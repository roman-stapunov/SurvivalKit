using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Domain;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ValueGeneration;
using survivalkitapi.Data;

namespace survivalkitapi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        private readonly ApplicationDbContext _dbContext;

        public UserController(ApplicationDbContext dbContext)
        {
            this._dbContext = dbContext;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<User>>> GetUsers()
        {
            return await this._dbContext.Users.ToListAsync();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<User>> GetUser(long id)
        {
            var user = await this._dbContext.Users.FindAsync(id);

            if (user == null)
            {
                return NotFound();
            }

            return user;
        }

        [HttpGet("api/{username}")]
        public async Task<ActionResult<User>> GetUserByUserName(string username)
        {
            var user = await this._dbContext.Users.FirstAsync(u => u.UserName == username);

            if (user == null)
            {
                return NotFound();
            }

            return user;
        }

        
        [HttpPost]
        public async Task<ActionResult<User>> PostUser(User user)
        {
            this._dbContext.Users.Add(user);
            await this._dbContext.SaveChangesAsync();
            
            return CreatedAtAction(nameof(GetUser), new {id = user.Id}, user);
        }
    }
}
