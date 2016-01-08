var numSelector = 1;
var indexes = [];
var pizzas = [];
var ingredients = [];
var prices = [];
//First of all, we populate the selector
$(document).ready(function(){
    $.get("../populateSelector",function(data){ //data = JSON array
        $.each(data,function(index,item){   //Iterate over the JSON array
            var array = $.map(item,function(el){ 
                return el;
            });
            
            indexes[index] = array[0];
            pizzas[index] = array[1];
            prices[index] = array[2];
            ingredients[index] = array[3];
        });
        populateSelect(numSelector,pizzas); //Method to generate the DIV
        setListener(numSelector);   //Method to change price label
        setPricesListener(numSelector); //Method to change the price showed

        //Setting listener onClick on 'Add more' button
        $('#add-button').on('click',function(){
            numSelector ++;
            populateSelect(numSelector,pizzas);
            setListener(numSelector);
            setPricesListener(numSelector);
        });
    });
});
function setListener(numSelector){
    //Dynamic change of selected price
    $('#selector'+numSelector).on('change',function(){
        var value = $(this).find(":selected").attr('value');
        var quantity = $('#quantitySelector'+numSelector).find(":selected").attr('value');
        $('#price' + numSelector).replaceWith('<h4 id=\"price' +
                numSelector + '\" name=\"' + prices[value] * quantity + '\"> \n\
                Total price: ' + prices[value] * quantity + '€</h4>');

    });
}

function setPricesListener(numSelector){
    $('#quantitySelector'+numSelector).on('change',function(){
       var quantity = $(this).find(":selected").attr('value'); //value of quantitySelector
       var elem = $('#selector'+numSelector).find(":selected").attr('value');    //selected pizza value
       $('#price' + numSelector).replaceWith('<h4 id=\"price' +
                numSelector + '\" name=\"' + prices[elem] * quantity + '\"> \n\
                Total price: ' + prices[elem] * quantity + '€</h4>');
    });
}

function populateSelect(numSelector, pizzas){
    //Create block for data
    var div = document.createElement('div');
    div.id = 'block-order';
    //Type label
    document.getElementById('order').appendChild(div);
    var typeLabel = document.createElement('h4');
    typeLabel.id = "type";
    typeLabel.innerHTML = "Pizza type: ";
    div.appendChild(typeLabel);
    //Populating selector tag
    var selector = document.createElement('select');
    selector.id= 'selector'+numSelector;
    selector.name = 'selector' + numSelector;
    selector.class = 'orderSelector';
    div.appendChild(selector);
    for(var i = 0; i < pizzas.length; i++){
        var option = document.createElement("option");
        option.value = i;
        option.innerHTML = pizzas[i];
        selector.appendChild(option);
    }
    //Quantity selector
    var quantitySelector = document.createElement('select');
    quantitySelector.id= 'quantitySelector'+numSelector;
    quantitySelector.name='quantitySelector'+numSelector;
    quantitySelector.class = 'orderSelector';
    div.appendChild(quantitySelector);
    for(var i = 1; i <= 5; i++){
        var option = document.createElement("option");
        option.value = i;
        option.innerHTML = i;
        quantitySelector.appendChild(option);
    }
    //Price label
    var priceLabel = document.createElement('h4');
    priceLabel.id = "price" + numSelector;
    priceLabel.innerHTML = "Total price: " + prices[0] + "€";
    div.appendChild(priceLabel);
    $('#price'+numSelector).attr("name",prices[0]);
}
   