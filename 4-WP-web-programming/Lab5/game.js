// Get the game-state element
const gameState = document.querySelector('.game-state');	

let isRunning  = true;
let currPlayer = 'X';
let aiPlayer   = 'O';
let board      = ['','','','','','','','',''];
let win 	   = false;
let draw       = false;
let playerStarted = 'X';

// Display Message Functions - return message
const winMessage  = () => `${currPlayer} Wins!`;
const drawMessage = () => `Draw.`;
const turnMessage = () => `Place ${currPlayer}`;	
const winStates   = [
	[0, 4, 8],
	[2, 4, 6],
	[0, 3, 6],
	[1, 4, 7],
	[2, 5, 8],
	[0, 1, 2],
	[3, 4, 5],
	[6, 7, 8]
];

// Init with the turn message
gameState.innerHTML = turnMessage();

function onCellClick(cellClickEvent) {
	// Cache the cell element
	const cell = cellClickEvent.target;
	// Get the cellId
	const cellId = parseInt(cell.getAttribute('cellId'));
	// Ignore filled cells
	if (board[cellId] !== '' || !isRunning)
		return;

	// Handle a cell play and check the board state
	onCellPlay(cell, cellId);
	onTurnEnd();
}

function onCellPlay(cell, cellId) {
	// Update the board state and the page
	board[cellId]  = currPlayer;
	cell.innerHTML = currPlayer;
}

function onResetGame() {
	if (playerStarted === 'X') {
		playerStarted = 'O';
		currPlayer = 'O';
		aiPlayer = 'X';
	}
	else {
		playerStarted = 'X';
		currPlayer = 'X';
		aiPlayer = 'O';
	}
	currPlayer = playerStarted;
	board  = ['','','','','','','','',''];
	document.querySelectorAll('.cell').forEach(cell => cell.innerHTML = '');
	gameState.innerHTML = turnMessage();
	win = false;
	draw = false;
	isRunning = true;
	if (aiPlayer === 'X')
		onPlayerChange();
}

function onPlayerChange() {
	currPlayer = currPlayer === 'X' ? 'O' : 'X';
	if (currPlayer === aiPlayer) {
		onAIMove();
	}
	if(isRunning)
		gameState.innerHTML = turnMessage();
}

function onAIMove() {
	let action = false;
	let cells  = Array.from(document.querySelectorAll('.cell'));
	// Check lines for win-condition
	if(!action)
		for(let i=0; i<3; i++) {
			if (board[i] === aiPlayer && board[i+1] == aiPlayer && board[i+2] === '') {
				onCellPlay(cells[i+2], i+2);
				action = true;
				break;
			}
		}
	// Check columns for win-condition
	if(!action)
		for(let i=0; i<3; i++) {
			if (board[i] === aiPlayer && board[i+3] == aiPlayer  && board[i+6] === '') {
				onCellPlay(cells[i+6], i+6);
				action = true;
				break;
			}
		}
	// Check diagonals for win-condition
	if (!action && board[0] === aiPlayer && board[4] === aiPlayer  && board[8] === '') {
		onCellPlay(cells[8], 9);
		action = true;
	}
	if (!action && board[3] === aiPlayer && board[5] === aiPlayer && board[6] === '') {
		onCellPlay(cells[6], 6);
		action = true;
	}
	// Check center 
	if (!action && board[4] === '') {
		onCellPlay(cells[4], 4);
		action = true;
	}
	
	// Random action
	while(!action) {
		let tile = Math.floor(Math.random() * 9);
		if (board[tile] === '') {
			action = true;
			onCellPlay(cells[tile], tile);
		}
	}
	onTurnEnd();
}

function onTurnEnd() {
	for(let i=0; i<8; i++) {
		let cell1 = board[winStates[i][0]];
		let cell2 = board[winStates[i][1]];
		let cell3 = board[winStates[i][2]];

		if (cell1 === cell2 && cell2 === cell3 && cell1 !== '') {
			win = true;
			break;
		}
	}
	if (win) {
		gameState.innerHTML = winMessage();
		isRunning = false;
		return;
	} 
	else if (!board.includes(""))
	{
		gameState.innerHTML = drawMessage();
		isRunning = false;
		return;
	}

	onPlayerChange();
}

// Get all cell elements and set listeners on each of them. Returns a NodeList
document.querySelectorAll('.cell').forEach(cell => cell.addEventListener('click', onCellClick));
// Get the (first element) restart button and set its listener
document.querySelector('.game-reset-button').addEventListener('click', onResetGame);