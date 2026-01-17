using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace DMA_Lab313.Models
{
    [Table("SEX")]
    public class Sex
    {
        [Key]
        public int SexID { get; set; }

        [StringLength(255)]
        public string? SexName { get; set; }

        // Navigation property
        [JsonIgnore]
        public virtual ICollection<Diploma>? Diplomas { get; set; }
    }
}
