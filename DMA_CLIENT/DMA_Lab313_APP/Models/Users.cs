namespace DMA_Lab313_APP.Models
{
    using System.ComponentModel.DataAnnotations;

    public class Users
    {
        public int UsersID { get; set; }

        [Required(ErrorMessage = "Username is required")]
        public string? UserName { get; set; }

        [Required(ErrorMessage = "Password is required")]
        public string? Password { get; set; }

        public string? Description { get; set; }
    }
}
