using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace DMA_Lab313.Models
{
    [Table("TRAINING_TYPE")]
    public class TrainingType
    {
        [Key]
        public int TrainingTypeID { get; set; }

        [StringLength(50)]
        public string? TrainingTypeCode { get; set; }

        [StringLength(255)]
        public string? TrainingTypeName { get; set; }

        // Navigation properties
        [JsonIgnore]
        public virtual ICollection<TrainingProgram>? TrainingPrograms { get; set; }
        [JsonIgnore]
        public virtual ICollection<Diploma>? Diplomas { get; set; }
    }
}
