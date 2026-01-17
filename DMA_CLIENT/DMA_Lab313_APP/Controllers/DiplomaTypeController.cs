using Microsoft.AspNetCore.Mvc;
using DMA_Lab313_APP.Models;
using System.Text;
using System.Text.Json;

namespace DMA_Lab313_APP.Controllers
{
    public class DiplomaTypeController : Controller
    {
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public DiplomaTypeController(IHttpClientFactory httpClientFactory, IConfiguration configuration)
        {
            _httpClient = httpClientFactory.CreateClient();
            _apiBaseUrl = configuration["ApiSettings:BaseUrl"] ?? "https://localhost:7114/api";
        }

        private bool IsLoggedIn()
        {
            return HttpContext.Session.GetString("UserName") != null;
        }

        // GET: DiplomaType
        public async Task<IActionResult> Index()
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var response = await _httpClient.GetAsync($"{_apiBaseUrl}/DiplomaType");
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var types = JsonSerializer.Deserialize<List<DiplomaType>>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return View(types);
            }
            return View(new List<DiplomaType>());
        }

        // GET: DiplomaType/Create
        public IActionResult Create()
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");
            return View();
        }

        // POST: DiplomaType/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(DiplomaType diplomaType)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var content = new StringContent(JsonSerializer.Serialize(diplomaType), Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync($"{_apiBaseUrl}/DiplomaType", content);
            
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction(nameof(Index));
            }
            
            ModelState.AddModelError("", "Error creating diploma type");
            return View(diplomaType);
        }

        // GET: DiplomaType/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var response = await _httpClient.GetAsync($"{_apiBaseUrl}/DiplomaType/{id}");
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var diplomaType = JsonSerializer.Deserialize<DiplomaType>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return View(diplomaType);
            }
            return NotFound();
        }

        // POST: DiplomaType/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, DiplomaType diplomaType)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            if (id != diplomaType.DiplomaTypeID)
            {
                return BadRequest();
            }

            var content = new StringContent(JsonSerializer.Serialize(diplomaType), Encoding.UTF8, "application/json");
            var response = await _httpClient.PutAsync($"{_apiBaseUrl}/DiplomaType/{id}", content);
            
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction(nameof(Index));
            }
            
            ModelState.AddModelError("", "Error updating diploma type");
            return View(diplomaType);
        }

        // GET: DiplomaType/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var response = await _httpClient.GetAsync($"{_apiBaseUrl}/DiplomaType/{id}");
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var diplomaType = JsonSerializer.Deserialize<DiplomaType>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return View(diplomaType);
            }
            return NotFound();
        }

        // POST: DiplomaType/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            await _httpClient.DeleteAsync($"{_apiBaseUrl}/DiplomaType/{id}");
            return RedirectToAction(nameof(Index));
        }
    }
}
