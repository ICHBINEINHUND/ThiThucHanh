using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using DMA_Lab313.Data;
using DMA_Lab313.Models;

namespace DMA_Lab313.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DiplomaTypeController : ControllerBase
    {
        private readonly DLMMSContext _context;

        public DiplomaTypeController(DLMMSContext context)
        {
            _context = context;
        }

        // GET: api/DiplomaType
        [HttpGet]
        public async Task<ActionResult<IEnumerable<DiplomaType>>> GetDiplomaTypes()
        {
            return await _context.DiplomaTypes.ToListAsync();
        }

        // GET: api/DiplomaType/5
        [HttpGet("{id}")]
        public async Task<ActionResult<DiplomaType>> GetDiplomaType(int id)
        {
            var diplomaType = await _context.DiplomaTypes.FindAsync(id);

            if (diplomaType == null)
            {
                return NotFound();
            }

            return diplomaType;
        }

        // PUT: api/DiplomaType/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutDiplomaType(int id, DiplomaType diplomaType)
        {
            if (id != diplomaType.DiplomaTypeID)
            {
                return BadRequest();
            }

            _context.Entry(diplomaType).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DiplomaTypeExists(id))
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

        // POST: api/DiplomaType
        [HttpPost]
        public async Task<ActionResult<DiplomaType>> PostDiplomaType(DiplomaType diplomaType)
        {
            _context.DiplomaTypes.Add(diplomaType);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetDiplomaType", new { id = diplomaType.DiplomaTypeID }, diplomaType);
        }

        // DELETE: api/DiplomaType/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDiplomaType(int id)
        {
            var diplomaType = await _context.DiplomaTypes.FindAsync(id);
            if (diplomaType == null)
            {
                return NotFound();
            }

            _context.DiplomaTypes.Remove(diplomaType);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool DiplomaTypeExists(int id)
        {
            return _context.DiplomaTypes.Any(e => e.DiplomaTypeID == id);
        }
    }
}
