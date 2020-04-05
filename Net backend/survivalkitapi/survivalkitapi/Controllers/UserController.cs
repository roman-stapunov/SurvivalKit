using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Domain;

namespace survivalkitapi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        private readonly ILogger<UserController> _logger;

        public UserController(ILogger<UserController> logger)
        {
            _logger = logger;
        }

        private User _user;

        [HttpPost]
        [ProducesResponseType(201)]
        [ProducesResponseType(400)]
        public ActionResult<User> Create(User user)
        {
            _user = user;
            return CreatedAtAction("GetById", user.Name);
        }

        public string GetById(long id)
        {
            return _user.Name;
        }

        [HttpGet]
        public IEnumerable<User> Get()
        {
            if (_user != null)
            {
                return new List<User>() { _user };
            }
            else
            {
                return new List<User>()
                {
                    new User()
                    {
                        Id = 0,
                        Name = "None",
                        Email = "None",
                        Password = "None",
                        PhoneNumber = "None",
                        Role = 0,
                        UserName = "None"
                    }
                };
            }
        }
    }
}
