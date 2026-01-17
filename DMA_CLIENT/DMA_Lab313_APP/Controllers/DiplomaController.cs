using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using DMA_Lab313_APP.Models;
using System.Text;
using System.Text.Json;

namespace DMA_Lab313_APP.Controllers
{
    public class DiplomaController : Controller
    {
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public DiplomaController(IHttpClientFactory httpClientFactory, IConfiguration configuration)
        {
            _httpClient = httpClientFactory.CreateClient();
            _apiBaseUrl = configuration["ApiSettings:BaseUrl"] ?? "https://localhost:7114/api";
        }

        private bool IsLoggedIn()
        {
            return HttpContext.Session.GetString("UserName") != null;
        }

        private async Task LoadDropdowns()
        {
            var sexResponse = await _httpClient.GetAsync($"{_apiBaseUrl}/Sex");
            var rankResponse = await _httpClient.GetAsync($"{_apiBaseUrl}/Rank");
            var diplomaTypeResponse = await _httpClient.GetAsync($"{_apiBaseUrl}/DiplomaType");
            var trainingTypeResponse = await _httpClient.GetAsync($"{_apiBaseUrl}/TrainingType");
            var trainingProgramResponse = await _httpClient.GetAsync($"{_apiBaseUrl}/TrainingProgram");

            var options = new JsonSerializerOptions { PropertyNameCaseInsensitive = true };

            if (sexResponse.IsSuccessStatusCode)
            {
                var sexes = JsonSerializer.Deserialize<List<Sex>>(await sexResponse.Content.ReadAsStringAsync(), options);
                ViewBag.SexList = new SelectList(sexes, "SexID", "SexName");
            }

            if (rankResponse.IsSuccessStatusCode)
            {
                var ranks = JsonSerializer.Deserialize<List<Rank>>(await rankResponse.Content.ReadAsStringAsync(), options);
                ViewBag.RankList = new SelectList(ranks, "RankID", "RankName");
            }

            if (diplomaTypeResponse.IsSuccessStatusCode)
            {
                var types = JsonSerializer.Deserialize<List<DiplomaType>>(await diplomaTypeResponse.Content.ReadAsStringAsync(), options);
                ViewBag.DiplomaTypeList = new SelectList(types, "DiplomaTypeID", "DiplomaTypeName");
            }

            if (trainingTypeResponse.IsSuccessStatusCode)
            {
                var types = JsonSerializer.Deserialize<List<TrainingType>>(await trainingTypeResponse.Content.ReadAsStringAsync(), options);
                ViewBag.TrainingTypeList = new SelectList(types, "TrainingTypeID", "TrainingTypeName");
            }

            if (trainingProgramResponse.IsSuccessStatusCode)
            {
                var programs = JsonSerializer.Deserialize<List<TrainingProgram>>(await trainingProgramResponse.Content.ReadAsStringAsync(), options);
                ViewBag.TrainingProgramList = new SelectList(programs, "TrainingProgramID", "TrainingProgramName");
            }
        }

        // GET: Diploma
        public async Task<IActionResult> Index(string? searchString)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            ViewData["CurrentFilter"] = searchString;

            string url = string.IsNullOrEmpty(searchString) 
                ? $"{_apiBaseUrl}/Diploma" 
                : $"{_apiBaseUrl}/Diploma/Search?strsearch={searchString}";

            var response = await _httpClient.GetAsync(url);
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var diplomas = JsonSerializer.Deserialize<List<Diploma>>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return View(diplomas);
            }
            return View(new List<Diploma>());
        }

        // GET: Diploma/Create
        public async Task<IActionResult> Create()
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");
            await LoadDropdowns();
            return View();
        }

        // POST: Diploma/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(Diploma diploma)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            // Remove navigation properties for serialization
            diploma.Sex = null;
            diploma.Rank = null;
            diploma.DiplomaType = null;
            diploma.TrainingType = null;
            diploma.TrainingProgram = null;

            var content = new StringContent(JsonSerializer.Serialize(diploma), Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync($"{_apiBaseUrl}/Diploma", content);
            
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction(nameof(Index));
            }
            
            await LoadDropdowns();
            ModelState.AddModelError("", "Error creating diploma");
            return View(diploma);
        }

        // GET: Diploma/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var response = await _httpClient.GetAsync($"{_apiBaseUrl}/Diploma/{id}");
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var diploma = JsonSerializer.Deserialize<Diploma>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                await LoadDropdowns();
                return View(diploma);
            }
            return NotFound();
        }

        // POST: Diploma/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, Diploma diploma)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            if (id != diploma.DiplomaID)
            {
                return BadRequest();
            }

            // Remove navigation properties for serialization
            diploma.Sex = null;
            diploma.Rank = null;
            diploma.DiplomaType = null;
            diploma.TrainingType = null;
            diploma.TrainingProgram = null;

            var content = new StringContent(JsonSerializer.Serialize(diploma), Encoding.UTF8, "application/json");
            var response = await _httpClient.PutAsync($"{_apiBaseUrl}/Diploma/{id}", content);
            
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction(nameof(Index));
            }
            
            await LoadDropdowns();
            ModelState.AddModelError("", "Error updating diploma");
            return View(diploma);
        }

        // GET: Diploma/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var response = await _httpClient.GetAsync($"{_apiBaseUrl}/Diploma/{id}");
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var diploma = JsonSerializer.Deserialize<Diploma>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return View(diploma);
            }
            return NotFound();
        }

        // POST: Diploma/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            await _httpClient.DeleteAsync($"{_apiBaseUrl}/Diploma/{id}");
            return RedirectToAction(nameof(Index));
        }
    }
}
