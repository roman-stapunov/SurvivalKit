using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Domain;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using survivalkitapi.Data;

namespace survivalkitapi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class RoleController
    {
        private readonly ApplicationDbContext _dbContext;

        public RoleController(ApplicationDbContext dbContext)
        {
            this._dbContext = dbContext;
        }
        
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Role>>> GetRoles()
        {
            return await this._dbContext.Roles.ToListAsync();
        }
    }
}