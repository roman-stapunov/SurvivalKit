using System.ComponentModel.DataAnnotations.Schema;

namespace Domain
{
    [Table("role")]
    public class Role
    {
        [Column("id")]
        public int Id { get; set; }
        
        [Column("role")]
        public string RoleName { get; set; }
    }
}