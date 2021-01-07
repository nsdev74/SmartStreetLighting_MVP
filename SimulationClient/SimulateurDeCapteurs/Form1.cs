using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Net.Http.Headers;
using System.Net;
using System.IO;
using System.Web.Script.Serialization;

namespace SimulateurDeCapteurs
{
    public partial class Form1 : Form
    {

        public Form1()
        {
            InitializeComponent();
        }
        IEnumerable<Postes> posts;
        BindingSource bs;
        static HttpClient client = new HttpClient { BaseAddress = new Uri("http://localhost:8080/") };
        static HttpResponseMessage response = client.GetAsync("Postes").Result;
        Postes selectedPost;


        private void Form1_Load(object sender, EventArgs e)
        {
            posts = response.Content.ReadAsAsync<IEnumerable<Postes>>().Result;
            bs = new BindingSource();
            bs.DataSource = posts;
            comboBox1.DataSource = bs ;
            comboBox1.DisplayMember = "id";
            comboBox1.ValueMember = "icon";
        }

        private async Task button1_ClickAsync(object sender, EventArgs e)
        {
            var values = new Dictionary<string, string>
            {
                { "longitude", "hello" },
                { "latitude", "world" },
                { "message", "Broken glass;<br> Unavailable since: 2021-01-07 at 08:38:27 GMT+01:00" },
                { "icon",  "0"}
            };

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://localhost:8080/Postes", content);

            var responseString = await response.Content.ReadAsStringAsync();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            label4.Visible = false;
            radioButton1.Checked = false;
            radioButton2.Checked = false;
            radioButton3.Checked = false;
            
            selectedPost = comboBox1.SelectedItem as Postes;
            
            textBox1.Text = selectedPost.longitude.ToString() + " , " + selectedPost.latitude.ToString();
            textBox2.Text = selectedPost.message;


            if (selectedPost.icon == 0)
            {
                radioButton1.Checked = true;
                textBox2.Enabled = true;
            }
            else if (selectedPost.icon == 1)
            {
                radioButton2.Checked = true;
            }
            else 
            {
                radioButton3.Checked = true;
            }

            

        }

        private void button1_Click(object sender, EventArgs e)
        {
            selectedPost = comboBox1.SelectedItem as Postes;

            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create("http://localhost:8080/Postes/"+selectedPost.id);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.MediaType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            int status=0;
            if(radioButton1.Checked==true)
            {
                status = 0;
            } else if (radioButton2.Checked==true)
            {
                status = 1;
            } else
            {
                status = 2;
            }

            

            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                streamWriter.Write("{\"longitude\" : " + selectedPost.longitude + "," +
                    "\"latitude\" : " + selectedPost.latitude + "," +
                    "\"message\" : \"" + textBox2.Text + "\"," +
                    "\"icon\" : " + status +
                    "}");
            }

            var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                JavaScriptSerializer jsSerializer = new JavaScriptSerializer();
                string json = streamReader.ReadToEnd();
            }

            response = client.GetAsync("Postes").Result;
            posts = response.Content.ReadAsAsync<IEnumerable<Postes>>().Result;
            bs.DataSource = posts;

            label4.Visible = true;

        }

        private void radioButton3_CheckedChanged(object sender, EventArgs e)
        {
            if (radioButton3.Checked == true)
            {
                textBox2.Text = "Active";
                textBox2.Enabled = false;
                label4.Visible = false;
            }
        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            if (radioButton2.Checked == true)
            {
                textBox2.Text = "Idle";
                textBox2.Enabled = false;
                label4.Visible = false;
            }
        }

        private void radioButton1_Click(object sender, EventArgs e)
        {
            if (radioButton1.Checked == true)
            {
                textBox2.Text = "Broken ;<br> Unavailable since: " + System.DateTime.Now.ToString();
                textBox2.Enabled = true;
                label4.Visible = false;
            }
        }
    }
}
