using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DMA_Lab313.Models
{
    [Table("DIPLOMA")]
    public class Diploma
    {
        [Key]
        public int DiplomaID { get; set; }

        [StringLength(60)]
        public string? FullName { get; set; }

        public DateTime? DOB { get; set; }

        public int? SexID { get; set; }

        public int? TrainingTypeID { get; set; }

        public int? TrainingProgramID { get; set; }

        public int? RankID { get; set; }

        public int? DiplomaTypeID { get; set; }

        [StringLength(100)]
        public string? BirthPlace { get; set; }

        [StringLength(20)]
        public string? VolumedBook { get; set; }

        [StringLength(30)]
        public string? DecideCode { get; set; }

        [StringLength(30)]
        public string? NumberSign { get; set; }

        [StringLength(30)]
        public string? RegisterNumber { get; set; }

        [StringLength(30)]
        public string? Language { get; set; }

        [StringLength(30)]
        public string? Colour { get; set; }

        [StringLength(30)]
        public string? Size { get; set; }

        // Navigation properties
        [ForeignKey("SexID")]
        public virtual Sex? Sex { get; set; }

        [ForeignKey("TrainingTypeID")]
        public virtual TrainingType? TrainingType { get; set; }

        [ForeignKey("TrainingProgramID")]
        public virtual TrainingProgram? TrainingProgram { get; set; }

        [ForeignKey("RankID")]
        public virtual Rank? Rank { get; set; }

        [ForeignKey("DiplomaTypeID")]
        public virtual DiplomaType? DiplomaType { get; set; }
    }
}
