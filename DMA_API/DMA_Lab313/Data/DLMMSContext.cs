using Microsoft.EntityFrameworkCore;
using DMA_Lab313.Models;

namespace DMA_Lab313.Data
{
    public class DLMMSContext : DbContext
    {
        public DLMMSContext(DbContextOptions<DLMMSContext> options) : base(options)
        {
        }

        public DbSet<Diploma> Diplomas { get; set; }
        public DbSet<DiplomaType> DiplomaTypes { get; set; }
        public DbSet<Rank> Ranks { get; set; }
        public DbSet<Sex> Sexes { get; set; }
        public DbSet<TrainingProgram> TrainingPrograms { get; set; }
        public DbSet<TrainingType> TrainingTypes { get; set; }
        public DbSet<Users> Users { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            // Configure relationships
            modelBuilder.Entity<Diploma>()
                .HasOne(d => d.Sex)
                .WithMany(s => s.Diplomas)
                .HasForeignKey(d => d.SexID);

            modelBuilder.Entity<Diploma>()
                .HasOne(d => d.Rank)
                .WithMany(r => r.Diplomas)
                .HasForeignKey(d => d.RankID);

            modelBuilder.Entity<Diploma>()
                .HasOne(d => d.DiplomaType)
                .WithMany(dt => dt.Diplomas)
                .HasForeignKey(d => d.DiplomaTypeID);

            modelBuilder.Entity<Diploma>()
                .HasOne(d => d.TrainingType)
                .WithMany(tt => tt.Diplomas)
                .HasForeignKey(d => d.TrainingTypeID);

            modelBuilder.Entity<Diploma>()
                .HasOne(d => d.TrainingProgram)
                .WithMany(tp => tp.Diplomas)
                .HasForeignKey(d => d.TrainingProgramID);

            modelBuilder.Entity<TrainingProgram>()
                .HasOne(tp => tp.TrainingType)
                .WithMany(tt => tt.TrainingPrograms)
                .HasForeignKey(tp => tp.TrainingTypeID);
        }
    }
}
