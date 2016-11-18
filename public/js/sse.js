$(document).ready(function() {
   console.log("ñskflsdkflsdf");
   var source = new EventSource("http://localhost:4892/emergencies", {'Accept':'application/json'});
   
   source.open = function()
   {
        console.log("Am I Working?");
   }

   source.onmessage = function(event) {
        console.log(event)
   };
});