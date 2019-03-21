package lib


 class FactionDictionary {

  val FactionJson = """{
                         |	"factions": [
                         |		{
                         |			"name": "akirasannomiya",
                         |			"miya": 4,
                         |			"mi2": 2,
                         |			"moz": 3,
                         |			"numa": 5,
                         |			"u": 1
                         |		},
                         |		{
                         |			"name": "haruyama",
                         |			"miya": 4,
                         |			"mi2": 2,
                         |			"moz": 3,
                         |			"numa": 5,
                         |			"u": 1
                         |		},
                         |		{
                         |			"name": "atsushi.coke.k",
                         |			"miya": 5,
                         |			"mi2": 2,
                         |			"moz": 3,
                         |			"numa": 4,
                         |			"u": 1
                         |		},
                         |		{
                         |			"name": "ayumi_takanaka",
                         |			"miya": 4,
                         |			"mi2": 2,
                         |			"moz": 3,
                         |			"numa": 5,
                         |			"u": 1
                         |		},
                         |		{
                         |			"name": "chihiro",
                         |			"miya": 3,
                         |			"mi2": 1,
                         |			"moz": 2,
                         |			"numa": 5,
                         |			"u": 4
                         |		}
                         |  ]
                         | }
                         |  """

   def get(name:String):Unit = {
     println("hello, " + name + "!!");
   }


 }

