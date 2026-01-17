using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace DMA_Lab313.Models
{
    [Table("RANK")]
    public class Rank
    {
        [Key]
        public int RankID { get; set; }

        [StringLength(50)]
        public string? RankName { get; set; }

        public float? FromPoint { get; set; }

        public float? ToPoint { get; set; }

        // Navigation property
        [JsonIgnore]
        public virtual ICollection<Diploma>? Diplomas { get; set; }
    }
}
