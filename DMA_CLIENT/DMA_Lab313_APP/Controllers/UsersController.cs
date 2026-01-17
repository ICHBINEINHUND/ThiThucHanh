using Microsoft.AspNetCore.Mvc;
using DMA_Lab313_APP.Models;
using System.Text;
using System.Text.Json;

namespace DMA_Lab313_APP.Controllers
{
    public class UsersController : Controller
    {
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public UsersController(IHttpClientFactory httpClientFactory, IConfiguration configuration)
        {
            _httpClient = httpClientFactory.CreateClient();
            _apiBaseUrl = configuration["ApiSettings:BaseUrl"] ?? "https://localhost:7114/api";
        }

        private bool IsLoggedIn()
        {
            return HttpContext.Session.GetString("UserName") != null;
        }

        // GET: Users
        public async Task<IActionResult> Index()
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var response = await _httpClient.GetAsync($"{_apiBaseUrl}/Users");
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var users = JsonSerializer.Deserialize<List<Users>>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return View(users);
            }
            return View(new List<Users>());
        }

        // GET: Users/Create
        public IActionResult Create()
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");
            return View();
        }

        // POST: Users/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(Users user)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            // Check if UserName already exists
            var checkResponse = await _httpClient.GetAsync($"{_apiBaseUrl}/Users");
            if (checkResponse.IsSuccessStatusCode)
            {
                var json = await checkResponse.Content.ReadAsStringAsync();
                var existingUsers = JsonSerializer.Deserialize<List<Users>>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                if (existingUsers != null && existingUsers.Any(u => u.UserName == user.UserName))
                {
                    ModelState.AddModelError("UserName", "UserName already exists!");
                    return View(user);
                }
            }

            var content = new StringContent(JsonSerializer.Serialize(user), Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync($"{_apiBaseUrl}/Users", content);
            
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction(nameof(Index));
            }
            
            ModelState.AddModelError("", "Error creating user");
            return View(user);
        }

        // GET: Users/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var response = await _httpClient.GetAsync($"{_apiBaseUrl}/Users/{id}");
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var user = JsonSerializer.Deserialize<Users>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return View(user);
            }
            return NotFound();
        }

        // POST: Users/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, Users user)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            if (id != user.UsersID)
            {
                return BadRequest();
            }

            var content = new StringContent(JsonSerializer.Serialize(user), Encoding.UTF8, "application/json");
            var response = await _httpClient.PutAsync($"{_apiBaseUrl}/Users/{id}", content);
            
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction(nameof(Index));
            }
            
            ModelState.AddModelError("", "Error updating user");
            return View(user);
        }

        // GET: Users/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            var response = await _httpClient.GetAsync($"{_apiBaseUrl}/Users/{id}");
            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                var user = JsonSerializer.Deserialize<Users>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return View(user);
            }
            return NotFound();
        }

        // POST: Users/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (!IsLoggedIn()) return RedirectToAction("Login", "Account");

            // Check if trying to delete Admin
            var getResponse = await _httpClient.GetAsync($"{_apiBaseUrl}/Users/{id}");
            if (getResponse.IsSuccessStatusCode)
            {
                var json = await getResponse.Content.ReadAsStringAsync();
                var user = JsonSerializer.Deserialize<Users>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                
                if (user?.UserName == "Admin")
                {
                    TempData["Error"] = "Cannot delete Admin user!";
                    return RedirectToAction(nameof(Index));
                }
            }

            var response = await _httpClient.DeleteAsync($"{_apiBaseUrl}/Users/{id}");
            return RedirectToAction(nameof(Index));
        }
    }
}
