using Domain;
using Microsoft.EntityFrameworkCore;

namespace survivalkitapi.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.HasSequence<int>("User_id_seq").StartsAt(2).IncrementsBy(1);

            modelBuilder.Entity<User>().Property(u => u.Id).HasDefaultValueSql("nextval('\"User_id_seq\"')");
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Role> Roles { get; set; }
    }
}