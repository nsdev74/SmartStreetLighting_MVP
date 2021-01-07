using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SimulateurDeCapteurs
{
    public class Postes
    {
        public int id { get; set; }
        public double longitude { get; set; }
        public double latitude { get; set; }
        public string message { get; set; }
        public int icon { get; set; }
    }
}
