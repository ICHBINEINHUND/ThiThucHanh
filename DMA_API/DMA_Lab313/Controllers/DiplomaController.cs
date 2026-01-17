using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using DMA_Lab313.Data;
using DMA_Lab313.Models;

namespace DMA_Lab313.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DiplomaController : ControllerBase
    {
        private readonly DLMMSContext _context;

        public DiplomaController(DLMMSContext context)
        {
            _context = context;
        }

        // GET: api/Diploma
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Diploma>>> GetDiplomas()
        {
            return await _context.Diplomas
                .Include(d => d.Sex)
                .Include(d => d.Rank)
                .Include(d => d.DiplomaType)
                .Include(d => d.TrainingType)
                .Include(d => d.TrainingProgram)
                .ToListAsync();
        }

        // GET: api/Diploma/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Diploma>> GetDiploma(int id)
        {
            var diploma = await _context.Diplomas
                .Include(d => d.Sex)
                .Include(d => d.Rank)
                .Include(d => d.DiplomaType)
                .Include(d => d.TrainingType)
                .Include(d => d.TrainingProgram)
                .FirstOrDefaultAsync(d => d.DiplomaID == id);

            if (diploma == null)
            {
                return NotFound();
            }

            return diploma;
        }

        // GET: api/Diploma/Search?strsearch=Hà Nội
        [HttpGet("Search")]
        public async Task<ActionResult<IEnumerable<Diploma>>> Search(string strsearch)
        {
            if (string.IsNullOrEmpty(strsearch))
            {
                return await _context.Diplomas
                    .Include(d => d.Sex)
                    .Include(d => d.Rank)
                    .Include(d => d.DiplomaType)
                    .Include(d => d.TrainingType)
                    .Include(d => d.TrainingProgram)
                    .ToListAsync();
            }

            var diplomas = await _context.Diplomas
                .Include(d => d.Sex)
                .Include(d => d.Rank)
                .Include(d => d.DiplomaType)
                .Include(d => d.TrainingType)
                .Include(d => d.TrainingProgram)
                .Where(d => d.FullName!.Contains(strsearch) || d.BirthPlace!.Contains(strsearch))
                .ToListAsync();

            return diplomas;
        }

        // PUT: api/Diploma/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutDiploma(int id, Diploma diploma)
        {
            if (id != diploma.DiplomaID)
            {
                return BadRequest();
            }

            // Clear navigation properties to avoid tracking issues
            diploma.Sex = null;
            diploma.Rank = null;
            diploma.DiplomaType = null;
            diploma.TrainingType = null;
            diploma.TrainingProgram = null;

            _context.Entry(diploma).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DiplomaExists(id))
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

        // POST: api/Diploma
        [HttpPost]
        public async Task<ActionResult<Diploma>> PostDiploma(Diploma diploma)
        {
            _context.Diplomas.Add(diploma);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetDiploma", new { id = diploma.DiplomaID }, diploma);
        }

        // DELETE: api/Diploma/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDiploma(int id)
        {
            var diploma = await _context.Diplomas.FindAsync(id);
            if (diploma == null)
            {
                return NotFound();
            }

            _context.Diplomas.Remove(diploma);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool DiplomaExists(int id)
        {
            return _context.Diplomas.Any(e => e.DiplomaID == id);
        }
    }
}
