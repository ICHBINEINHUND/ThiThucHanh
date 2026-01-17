using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace DMA_Lab313.Models
{
    [Table("DIPLOMA_TYPE")]
    public class DiplomaType
    {
        [Key]
        public int DiplomaTypeID { get; set; }

        [Column("DilomaTypeName")]
        [StringLength(255)]
        public string DiplomaTypeName { get; set; } = null!;

        [StringLength(255)]
        public string? OrganizationIssue { get; set; }

        [StringLength(255)]
        public string? PersonIssue { get; set; }

        [StringLength(255)]
        public string? Position { get; set; }

        [StringLength(50)]
        public string? Language { get; set; }

        [StringLength(50)]
        public string? Colour { get; set; }

        [StringLength(50)]
        public string? Size { get; set; }

        // Navigation property
        [JsonIgnore]
        public virtual ICollection<Diploma>? Diplomas { get; set; }
    }
}
