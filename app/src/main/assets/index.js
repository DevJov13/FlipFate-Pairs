const gridContainer = document.querySelector(".grid-container");
let cards = [];
let firstCard, secondCard;
let lockBoard = false;
let score = 0;

document.querySelector(".score").textContent = score;

// Access JSON data directly from the script block
const cardDataElement = document.getElementById('cardData');
const jsonData = JSON.parse(cardDataElement.textContent);
cards = [...jsonData.cards, ...jsonData.cards];

shuffleCards();
generateCards();

function shuffleCards() {
  let currentIndex = cards.length,
    randomIndex,
    temporaryValue;
  while (currentIndex !== 0) {
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;
    temporaryValue = cards[currentIndex];
    cards[currentIndex] = cards[randomIndex];
    cards[randomIndex] = temporaryValue;
  }
}

function generateCards() {
  for (let card of cards) {
    const cardElement = document.createElement("div");
    cardElement.classList.add("card");
    cardElement.setAttribute("data-name", card.name);
    cardElement.innerHTML = `
      <div class="front">
        <img class="front-image" src=${card.image} />
      </div>
      <div class="back"></div>
    `;
    gridContainer.appendChild(cardElement);
    cardElement.addEventListener("click", flipCard);
  }
}
// Add this line at the beginning to get a reference to the audio element
const flipSound = document.getElementById("flipSound");


function toggleMute() {
  const flipSound = document.getElementById("flipSound");
  const matchSound = document.getElementById("matchSound");

  flipSound.muted = !flipSound.muted;
  matchSound.muted = !matchSound.muted;
}


function flipCard() {
  if (lockBoard) return;
  if (this === firstCard) return;

  this.classList.add("flipped");
  flipSound.play();
  if (!firstCard) {
    firstCard = this;
    return;
  }

  secondCard = this;
  score++;
  document.querySelector(".score").textContent = score;
  lockBoard = true;

  checkForMatch();
}

const matchSound = document.getElementById("matchSound");

function checkForMatch() {
  let isMatch = firstCard.dataset.name === secondCard.dataset.name;

  if (isMatch) {
    disableCards();
    matchSound.play(); // Play the matching sound effect
    showFloatingMessage("Nice!"); // Call the function to display the floating message
  } else {
    unflipCards();
  }
}

function disableCards() {
  firstCard.removeEventListener("click", flipCard);
  secondCard.removeEventListener("click", flipCard);

  resetBoard();
}

function unflipCards() {
  setTimeout(() => {
    firstCard.classList.remove("flipped");
    secondCard.classList.remove("flipped");
    resetBoard();
  }, 1000);
}

function resetBoard() {
  firstCard = null;
  secondCard = null;
  lockBoard = false;
}

function restart() {
  resetBoard();
  shuffleCards();
  score = 0;
  document.querySelector(".score").textContent = score;
  gridContainer.innerHTML = "";
  generateCards();
}

function showFloatingMessage() {
  // Define an array of possible messages
  const messages = ["😁", "😊", "😇"];

  // Randomly select a message
  const randomIndex = Math.floor(Math.random() * messages.length);
  const selectedMessage = messages[randomIndex];

  const floatingMessage = document.createElement("div");
  floatingMessage.classList.add("floating-message");
  floatingMessage.textContent = selectedMessage;
  document.body.appendChild(floatingMessage);

  // Remove the floating message after a short delay
  setTimeout(() => {
    document.body.removeChild(floatingMessage);
  }, 2000); // Adjust the duration as needed
  
}


//For animation bg
//document.body.addEventListener("pointermove", (e)=>{
 // const { currentTarget: el, clientX: x, clientY: y } = e;
 // const { top: t, left: l, width: w, height: h } = el.getBoundingClientRect();
 // el.style.setProperty('--posX',  x-l-w/2);
 // el.style.setProperty('--posY',  y-t-h/2);
//})