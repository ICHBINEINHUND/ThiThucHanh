namespace DMA_Lab313_APP.Models
{
    using System.ComponentModel.DataAnnotations;

    public class Diploma
    {
        public int DiplomaID { get; set; }

        [Required(ErrorMessage = "Full Name is required")]
        public string? FullName { get; set; }

        [Required(ErrorMessage = "Date of Birth is required")]
        public DateTime? DOB { get; set; }

        [Required(ErrorMessage = "Sex is required")]
        public int? SexID { get; set; }

        [Required(ErrorMessage = "Training Type is required")]
        public int? TrainingTypeID { get; set; }

        [Required(ErrorMessage = "Training Program is required")]
        public int? TrainingProgramID { get; set; }

        [Required(ErrorMessage = "Rank is required")]
        public int? RankID { get; set; }

        [Required(ErrorMessage = "Diploma Type is required")]
        public int? DiplomaTypeID { get; set; }

        [Required(ErrorMessage = "Birth Place is required")]
        public string? BirthPlace { get; set; }

        [Required(ErrorMessage = "Volumed Book is required")]
        public string? VolumedBook { get; set; }

        [Required(ErrorMessage = "Decide Code is required")]
        public string? DecideCode { get; set; }

        [Required(ErrorMessage = "Number Sign is required")]
        public string? NumberSign { get; set; }

        [Required(ErrorMessage = "Register Number is required")]
        public string? RegisterNumber { get; set; }
        public string? Language { get; set; }
        public string? Colour { get; set; }
        public string? Size { get; set; }

        // Navigation properties for display
        public Sex? Sex { get; set; }
        public Rank? Rank { get; set; }
        public DiplomaType? DiplomaType { get; set; }
        public TrainingType? TrainingType { get; set; }
        public TrainingProgram? TrainingProgram { get; set; }
    }
}
