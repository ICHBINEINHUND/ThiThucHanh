using Microsoft.AspNetCore.Mvc;
using DMA_Lab313_APP.Models;
using System.Text.Json;

namespace DMA_Lab313_APP.Controllers
{
    public class AccountController : Controller
    {
        private readonly HttpClient _httpClient;
        private readonly string _apiBaseUrl;

        public AccountController(IHttpClientFactory httpClientFactory, IConfiguration configuration)
        {
            _httpClient = httpClientFactory.CreateClient();
            _apiBaseUrl = configuration["ApiSettings:BaseUrl"] ?? "https://localhost:7114/api";
            _httpClient.BaseAddress = new Uri(_apiBaseUrl);
        }

        [HttpGet]
        public IActionResult Login()
        {
            // If already logged in, redirect to Users
            if (HttpContext.Session.GetString("UserName") != null)
            {
                return RedirectToAction("Index", "Users");
            }
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(LoginViewModel model)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }

            try
            {
                var response = await _httpClient.GetAsync($"{_apiBaseUrl}/Users/CheckLogin?UserName={model.UserName}&Password={model.Password}");
                
                if (response.IsSuccessStatusCode)
                {
                    var json = await response.Content.ReadAsStringAsync();
                    var user = JsonSerializer.Deserialize<Users>(json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                    
                    if (user != null)
                    {
                        HttpContext.Session.SetString("UserName", user.UserName ?? "");
                        HttpContext.Session.SetInt32("UsersID", user.UsersID);
                        return RedirectToAction("Index", "Users");
                    }
                }
                
                ModelState.AddModelError("", "Invalid username or password");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", "Error connecting to server: " + ex.Message);
            }

            return View(model);
        }

        public IActionResult Logout()
        {
            HttpContext.Session.Clear();
            return RedirectToAction("Login");
        }
    }
}
