namespace DMA_Lab313_APP.Models
{
    using System.ComponentModel.DataAnnotations;

    public class DiplomaType
    {
        public int DiplomaTypeID { get; set; }

        [Required(ErrorMessage = "Diploma Type Name is required")]
        public string DiplomaTypeName { get; set; } = null!;
        
        public string? OrganizationIssue { get; set; }
        public string? PersonIssue { get; set; }
        public string? Position { get; set; }
        public string? Language { get; set; }
        public string? Colour { get; set; }
        public string? Size { get; set; }
    }
}
