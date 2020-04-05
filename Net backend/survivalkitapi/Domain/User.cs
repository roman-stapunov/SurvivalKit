using System;
namespace Domain
{
    public class User
    {

        public long Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string UserName { get; set; }
        public string PhoneNumber { get; set; }
        public string Password { get; set; }
        public long Role { get; set; }
    }
}
