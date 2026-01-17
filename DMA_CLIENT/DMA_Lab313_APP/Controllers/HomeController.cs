using Microsoft.AspNetCore.Mvc;

namespace DMA_Lab313_APP.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            if (HttpContext.Session.GetString("UserName") == null)
            {
                return RedirectToAction("Login", "Account");
            }
            return RedirectToAction("Index", "Users");
        }

        public IActionResult Error()
        {
            return View();
        }
    }
}
