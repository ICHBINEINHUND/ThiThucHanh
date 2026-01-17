using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using DMA_Lab313.Data;
using DMA_Lab313.Models;

namespace DMA_Lab313.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TrainingProgramController : ControllerBase
    {
        private readonly DLMMSContext _context;

        public TrainingProgramController(DLMMSContext context)
        {
            _context = context;
        }

        // GET: api/TrainingProgram
        [HttpGet]
        public async Task<ActionResult<IEnumerable<TrainingProgram>>> GetTrainingPrograms()
        {
            return await _context.TrainingPrograms
                .Include(tp => tp.TrainingType)
                .ToListAsync();
        }

        // GET: api/TrainingProgram/5
        [HttpGet("{id}")]
        public async Task<ActionResult<TrainingProgram>> GetTrainingProgram(int id)
        {
            var trainingProgram = await _context.TrainingPrograms
                .Include(tp => tp.TrainingType)
                .FirstOrDefaultAsync(tp => tp.TrainingProgramID == id);

            if (trainingProgram == null)
            {
                return NotFound();
            }

            return trainingProgram;
        }

        // PUT: api/TrainingProgram/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTrainingProgram(int id, TrainingProgram trainingProgram)
        {
            if (id != trainingProgram.TrainingProgramID)
            {
                return BadRequest();
            }

            // Clear navigation property to avoid tracking issues
            trainingProgram.TrainingType = null;

            _context.Entry(trainingProgram).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TrainingProgramExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/TrainingProgram
        [HttpPost]
        public async Task<ActionResult<TrainingProgram>> PostTrainingProgram(TrainingProgram trainingProgram)
        {
            _context.TrainingPrograms.Add(trainingProgram);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTrainingProgram", new { id = trainingProgram.TrainingProgramID }, trainingProgram);
        }

        // DELETE: api/TrainingProgram/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTrainingProgram(int id)
        {
            var trainingProgram = await _context.TrainingPrograms.FindAsync(id);
            if (trainingProgram == null)
            {
                return NotFound();
            }

            _context.TrainingPrograms.Remove(trainingProgram);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool TrainingProgramExists(int id)
        {
            return _context.TrainingPrograms.Any(e => e.TrainingProgramID == id);
        }
    }
}
