using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DMA_Lab313.Models
{
    [Table("USERS")]
    public class Users
    {
        [Key]
        public int UsersID { get; set; }

        [StringLength(50)]
        public string? UserName { get; set; }

        [StringLength(255)]
        public string? Password { get; set; }

        [Column("Discription")]
        [StringLength(255)]
        public string? Description { get; set; }
    }
}
