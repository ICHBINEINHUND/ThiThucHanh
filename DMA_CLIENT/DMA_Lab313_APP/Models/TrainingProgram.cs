namespace DMA_Lab313_APP.Models
{
    public class TrainingProgram
    {
        public int TrainingProgramID { get; set; }
        public string? TrainingProgramName { get; set; }
        public float? MaxPoint { get; set; }
        public float? AchievePoint { get; set; }
        public int? Achieve { get; set; }
        public int? TrainingTypeID { get; set; }
        public TrainingType? TrainingType { get; set; }
    }
}
