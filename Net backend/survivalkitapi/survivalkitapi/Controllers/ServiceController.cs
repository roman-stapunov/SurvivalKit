using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Domain;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using survivalkitapi.Data;


namespace survivalkitapi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ServiceController : ControllerBase
    {
        private readonly ApplicationDbContext _dbContext;

        public ServiceController(ApplicationDbContext dbContext)
        {
            this._dbContext = dbContext;
        }
        
        [HttpGet("{id}")]
        public async Task<ActionResult<Service>> GetService(long id)
        {
            var service = await this._dbContext.Services.FindAsync(id);

            if (service == null)
            {
                return NotFound();
            }

            return service;
        }

        [HttpGet("api/{serviceProviderId}")]
        public async Task<ActionResult<IEnumerable<Service>>> GetServicesByProvider(long serviceProviderId)
        {
            var services = await this._dbContext.Services.Where(s => s.ServiceProviderId == serviceProviderId).ToListAsync();

            return services;
        }
        
        [HttpPost]
        public async Task<ActionResult<Service>> PostService(Service service)
        {
            this._dbContext.Services.Add(service);
            await this._dbContext.SaveChangesAsync();
            
            return CreatedAtAction(nameof(GetService), new {id = service.Id}, service);
        }
    }
}