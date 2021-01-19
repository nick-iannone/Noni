

<!-- INSPO -->
MONSTER TRAIN - roguelike deck-builder
SLAY THE SPIRE - roguelike deck-builder
GWENT - Witcher card game
DICEY DUNGEONS - dice-rolling card game
INTO THE BREACH - roguelike turn-based puzzle


<!-- GAME ARCHITECTURE -->
game: {
    playerOne: {
        username: '',
        isTurn: false,
        turnCount: 0,
        turn: {
            begin: false,
            plan: false,
            combat: false,
            plan2: false,
            end: false,
        },
        gameState: [
            deck: {
                cards: [],
            },
            hand: {
                cards: [],
            },
            combatZone: {
                cards: [],
                mods: [],
            },
            deadZone: {
                cards: []
            },
            contracts: {
                avaCont: [],
                actCont: [],
                compCont: [],
                mods: []
            },
            fortress: {
                HP: 0,
                mods: [],
            },
            reputation: {
                score: 0,
                mods: [],
            },
            resources: {
                cache: 0,
                mods: [],
            }
        ],
    },
    gameLog: [],
},


<!-- BEANS -->
contract: {
    id: 0,
    name: '',
    cost: '',
    desc: '',
    dura: 0,
    emits: [],
    mods: []
},
card: {
    id: 0,
    name: '',
    cost: '',
    ability: '',
    flavor: '',
    att: 0,
    res: 0,
    loy: 0,
    emits: [],
    mods: [],
    media: {
        image: '',
        eSound: '',
        aSound: '',
        dSound: ''
    }
},
user: {
    id: 0,
    username: '',
    rank: '',
    collection: [],
    decks: [],
    history: []
},
deck: {
    name: '',
    ownerUsername: '',
    cards: [],
    image: ''
},

TROOP MODS OBJECT

Loyal Peasant - If there is another allied troop in the combat zone, this troop gains +1 Resilience.

loyalPeasant() {
    let isInPlay = false;
    for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        if (this.game.myGame.combatZone.cards[i].name === 'Loyal Peasant') {
            isInPlay = true;
            if (this.game.myGame.combatZone.cards.length >= 2) {
                this.game.myGame.combatZone.cards[i].resilience += 1;
            }
        }
}

Valiant Knight - Give all other allied troops in the combat zone +1 Loyalty.

valiantKnight() {

    for (var i = 0; i < this.game.myGame.combatZone.cards.length; i++) {
        this.game.myGame.combatZone.cards[i].loyalty += 1;
    }
}


Phalanx - This troop gets +1 Attack and +1 Resilience for each other troop in the combat zone.


Pinnacle Assassin - This troop may attack Reputation directly. When this troop attacks a fortess, destory up to one opposing troop.

Veteran Archers - Whenever this troop attacks, it deals one damage to each enemy troop.

Battering Ram - This troop only deals damage to fortresses.

Catapult - If this troop damages a fortress, it deals twice as much damage instead.

Thunderous Cavalry - This troop can only attack if there is an enemy troop in the opposing combat zone.

Veteran Healer - While this troop is in the combat zone, if another allied troop would take damage, reduce the damage by 2.
