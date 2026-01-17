using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using DMA_Lab313.Data;
using DMA_Lab313.Models;

namespace DMA_Lab313.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SexController : ControllerBase
    {
        private readonly DLMMSContext _context;

        public SexController(DLMMSContext context)
        {
            _context = context;
        }

        // GET: api/Sex
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Sex>>> GetSexes()
        {
            return await _context.Sexes.ToListAsync();
        }

        // GET: api/Sex/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Sex>> GetSex(int id)
        {
            var sex = await _context.Sexes.FindAsync(id);

            if (sex == null)
            {
                return NotFound();
            }

            return sex;
        }

        // PUT: api/Sex/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutSex(int id, Sex sex)
        {
            if (id != sex.SexID)
            {
                return BadRequest();
            }

            _context.Entry(sex).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SexExists(id))
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

        // POST: api/Sex
        [HttpPost]
        public async Task<ActionResult<Sex>> PostSex(Sex sex)
        {
            _context.Sexes.Add(sex);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetSex", new { id = sex.SexID }, sex);
        }

        // DELETE: api/Sex/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteSex(int id)
        {
            var sex = await _context.Sexes.FindAsync(id);
            if (sex == null)
            {
                return NotFound();
            }

            _context.Sexes.Remove(sex);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool SexExists(int id)
        {
            return _context.Sexes.Any(e => e.SexID == id);
        }
    }
}
