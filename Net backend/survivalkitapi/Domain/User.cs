using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Domain
{
    [Table("User")]
    public class User
    {

        [Column("id")]
        [Key]
        public long Id { get; set; }
        
        [Column("name")]
        public string Name { get; set; }
        [Column("email")]
        public string Email { get; set; }
        [Column("username")]
        public string UserName { get; set; }
        
        [Column("phonenumber")]
        public string PhoneNumber { get; set; }
        [Column("password")]
        public string Password { get; set; }
        [Column("role")]
        public int RoleName { get; set; }
    }
}
