$(document).ready(function() {
   console.log("ñskflsdkflsdf");
   var source = new EventSource("https://localhost:4892/emergencies", {'Accept':'application/json'});
   
   source.onopen = function()
   {
        console.log("Am I Working?");
   }

   source.onmessage = function(event) {
        console.log(event.data)
   };
});