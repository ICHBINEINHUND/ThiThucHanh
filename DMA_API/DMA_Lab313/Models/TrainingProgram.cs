using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace DMA_Lab313.Models
{
    [Table("TRAINING_PROGRAM")]
    public class TrainingProgram
    {
        [Key]
        public int TrainingProgramID { get; set; }

        [Column("TrainingProgram")]
        [StringLength(255)]
        public string? TrainingProgramName { get; set; }

        public float? MaxPoint { get; set; }

        public float? AchievePoint { get; set; }

        public int? Achieve { get; set; }

        public int? TrainingTypeID { get; set; }

        // Navigation properties
        [ForeignKey("TrainingTypeID")]
        public virtual TrainingType? TrainingType { get; set; }

        [JsonIgnore]
        public virtual ICollection<Diploma>? Diplomas { get; set; }
    }
}
