using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Domain
{
    [Table("service")]
    public class Service
    {
        [Column("id")]
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }

        [Column("serviceproviderid")]
        public long ServiceProviderId { get; set; }
        
        [Column("servicename")]
        public string ServiceName { get; set; }
        
        [Column("description")]
        public string Description { get; set; }
        
        [Column("imageurl")]
        public string ImageUrl { get; set; }
        
        [Column("price")]
        public double Price { get; set; }
        
        [Column("rewardcoins")]
        public long RewardCoins { get; set; }
    }
}