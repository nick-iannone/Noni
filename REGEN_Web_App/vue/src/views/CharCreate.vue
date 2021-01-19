<template>
  <div id='charcreate'>
    <hr id='page-topper'>  
    <div class='charhead-tile'>
          <h1 class='char-header'>Character Creation</h1> 
    </div>
    <hr>
    <div >
        <form class='char-form'>
            <div class='char-field'>
                <label for='name'>Name: </label>
                <input id='name' type='text' maxlength="17" v-model="newChar.name"/>
            </div>
            <div class='char-field'>
                <label for='race'>Race: </label>
                <select name='race' id='race' v-model="newChar.race">
                    <option disabled value="">Please Select One</option>
                    <option v-for="race in raceList" :key="race.raceID">{{race.name}}</option>
                </select>
            </div>
            <div class='char-field'>
                <label for='class'>Class: </label>
                <select name='pclass' id='pclass' v-model="newChar.charClass">
                    <option disabled value="">Please Select One</option>
                    <option v-for="pclass in classList" :key="pclass.classID">{{pclass.name}}</option>
                </select>
            </div>
            <div class='char-field'>
                <label for='weapon'>Weapon: </label>
                <select name='weapon' id='weapon' v-model="newChar.weapon">
                    <option disabled value="">Please Select One</option>
                    <option v-for="weapon in weaponList" :key="weapon.weaponID">{{weapon.name}}</option>
                </select>
            </div>
            <button v-if="newChar.weapon != '' && newChar.charClass != '' && newChar.race != '' && newChar.name != ''" v-on:click="saveCharacter(this.newChar, this.$store.state.user.id)" class='save-button'>Save Character</button>
        </form>
        <h2 class='name-bind'>{{newChar.name === '' ? '~ Name ~' : '~ '+newChar.name+' ~'}}</h2>
        <div class='char-display'>
            <div class='stat-bind' v-if="newChar.race != '' && newChar.class != '' && newChar.weapon != ''">
                <div class='row'>
                    <p class='stat-field'>AC: {{15 + raceMods(newChar.race).modAC + classMods(newChar.charClass).modAC}}</p>
                    <p class='stat-field'>Attack Mod: {{raceMods(newChar.race).modAttack + classMods(newChar.charClass).modAttack + weaponMods(newChar.weapon).modAttack}}</p>
                </div>
                <div class='row'>
                    <p class='stat-field'>HP: {{50 + raceMods(newChar.race).modHP + classMods(newChar.charClass).modHP}}</p>
                    <p class='stat-field'>Spell Mod: {{raceMods(newChar.race).modSpell + classMods(newChar.charClass).modSpell + weaponMods(newChar.weapon).modSpell}}</p>
                </div>
            </div>
            <div class='details-tile'>
                <p class="spell-details">{{show ? spellToShow.name + ' - ' + spellToShow.description : 'Select a class then click on a spell to see the details here.'}}</p>
            </div>    
            <div class='spells' v-if="newChar.charClass != ''">
                <div class='spell-list'> SPELLS: 
                    <button @click="spellToShow = spell; show=true" class='spell' target='blank' v-for="spell in calulateClassSpells(newChar.charClass)" :key="spell.spellID"> {{spell.name}} </button>
                </div>
            </div>
        </div>
        <div class='desc-container'>
            <div class='field-description'>
                <h3 class='field-header'>{{newChar.race === '' ? 'Race' : newChar.race}}</h3>
                <hr/>
                <p class='desc-text'>{{newChar.race === '' ? '*Description*':getRaceDetails(newChar.race)}}</p>
                <div class='mod-container'>
                    <p class='desc-mods'>Armor Class: {{newChar.race === '' ? 'None':raceMods(newChar.race).modAC}}</p>
                    <p class='desc-mods'>Hit Points: {{newChar.race === '' ? 'None':raceMods(newChar.race).modHP}}</p>
                    <p class='desc-mods'>Attack Mod: {{newChar.race === '' ? 'None':raceMods(newChar.race).modAttack}}</p>
                    <p class='desc-mods'>Spell Mod: {{newChar.race === '' ? 'None':raceMods(newChar.race).modSpell}}</p>
                </div>
            </div>
            <div class='field-description'>
                <h3 class='field-header'>{{newChar.charClass === '' ? 'Class' : newChar.charClass}}</h3>
                <hr/>
                <p class='desc-text'>{{newChar.charClass === '' ? '*Description*':getClassDetails(newChar.charClass)}}</p>
                <div class='mod-container'>
                    <p class='desc-mods'>Armor Class: {{newChar.charClass === '' ? 'None':classMods(newChar.charClass).modAC}}</p>
                    <p class='desc-mods'>Hit Points: {{newChar.charClass === '' ? 'None':classMods(newChar.charClass).modHP}}</p>
                    <p class='desc-mods'>Attack Mod: {{newChar.charClass === '' ? 'None':classMods(newChar.charClass).modAttack}}</p>
                    <p class='desc-mods'>Spell Mod: {{newChar.charClass === '' ? 'None':classMods(newChar.charClass).modSpell}}</p>
                </div>
            </div>
            <div class='field-description'>
                <h3 class='field-header'>{{newChar.weapon === '' ? 'Weapon' : newChar.weapon}}</h3>
                <hr/>
                <p class='desc-text'>{{newChar.weapon === '' ? '*Description*':getWeaponDetails(newChar.weapon)}}</p>
                <div class='mod-container'>
                    <p class='desc-mods'>Damage Die: {{newChar.weapon === '' ? 'None':'d' + weaponMods(newChar.weapon).damageDie}}</p>
                    <p class='desc-mods'>Attack Mod: {{newChar.weapon === '' ? 'None':weaponMods(newChar.weapon).modAttack}}</p>
                    <p class='desc-mods'>Spell Mod: {{newChar.weapon === '' ? 'None':weaponMods(newChar.weapon).modSpell}}</p>
                </div>
            </div>
        </div>    
    </div>
  </div>
</template>

<script>
import RegenService from '../services/RegenService.js'


export default {
    data() {
        return {
            newChar: {
                name: '',
                race: '',
                charClass: '',
                weapon: '',
                userID: this.$store.state.user.id
            },
            spellToShow: {},
            show: false,
            pclass: {},
            prace: {},
            pweapon: {},
            pspells: [],
            classList: [],
            weaponList: [],
            raceList: [],
            rogueSpellList: [],
            paladinSpellList: [],
            wizardSpellList: [],
            rangerSpellList: [],
            necroSpellList: [],
            warriorSpellList: []
        }
    },
    methods: {
        saveCharacter(newChar, id) {
            RegenService.saveCharacter(newChar, id);
        },
        getRaceDetails(name) {
            for (let i = 0; i < this.raceList.length; i++) {
                if (this.raceList[i].name === name) {
                    return this.raceList[i].description;
                }
            }
        },
        getClassDetails(name) {
            for (let i = 0; i < this.classList.length; i++) {
                if (this.classList[i].name === name) {
                    return this.classList[i].description;
                }
            }
        },
        getWeaponDetails(name) {
            for (let i = 0; i < this.weaponList.length; i++) {
                if (this.weaponList[i].name === name) {
                    return this.weaponList[i].description;
                }
            }
        },
        raceMods(name) {
            for (let i = 0; i < this.raceList.length; i++) {
                if (this.raceList[i].name === name) {
                    return this.raceList[i];
                }
            }
        },
        classMods(name) {
            for (let i = 0; i < this.classList.length; i++) {
                if (this.classList[i].name === name) {
                    return this.classList[i];
                }
            }
        },
        weaponMods(name) {
            for (let i = 0; i < this.weaponList.length; i++) {
                if (this.weaponList[i].name === name) {
                    return this.weaponList[i];
                }
            }
        },
        calulateClassSpells(name) {
            if (name == 'Rogue') {
                return this.rogueSpellList;
            }
            if (name == 'Paladin') {
                return this.paladinSpellList;
            }
            if (name == 'Wizard') {
                return this.wizardSpellList;
            }
            if (name == 'Ranger') {
                return this.rangerSpellList;
            }
            if (name == 'Necromancer') {
                return this.necroSpellList;
            }
            if (name == 'Warrior') {
                return this.warriorSpellList;
            }
        }
    },
    created() {
        RegenService.getRaceList().then(response => {
            this.raceList = response.data;
        });
        RegenService.getClassList().then(response => {
            this.classList = response.data;
        });
        RegenService.getWeaponList().then(response => {
            this.weaponList = response.data;
        });
        RegenService.getSpellList(1).then(response => {
            this.rogueSpellList = response.data;
        });
        RegenService.getSpellList(2).then(response => {
            this.paladinSpellList = response.data;
        });
        RegenService.getSpellList(3).then(response => {
            this.wizardSpellList = response.data;
        });
        RegenService.getSpellList(4).then(response => {
            this.rangerSpellList = response.data;
        });
        RegenService.getSpellList(5).then(response => {
            this.necroSpellList = response.data;
        });
        RegenService.getSpellList(6).then(response => {
            this.warriorSpellList = response.data;
        });
    }
}
</script>

<style>
#charcreate {
    background-image: url('../assets/char.png');
    display: flex;
    flex-direction: column;
    align-items: center;
    flex-wrap: wrap;
}
hr {
    width: 98%;
    height: 2px;
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
}
#page-topper {
    margin-top: 30px;
}
.char-header {
    font-size: 100px;
    font-family: 'Germania One';
    text-align: center;
    background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.95), rgb(115, 137, 179), rgba(255, 255, 255, 1));
	background-size: cover;
	color: transparent;
    font-weight: 400;
	background-clip: text;
	-webkit-background-clip: text;
    margin-top: 0;
    margin-bottom: 0px;
}
.char-form {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    padding-top: 20px;
    justify-content: space-evenly;
}
.char-form label {
    color: white;
    font-family: 'Germania One';
    font-size: 30px;
    padding: 20px;
}
.char-field {
    display: flex;
    align-items: center;
}
#name {
    width: 300px;
    height: 30px;
}
#race {
    width: 300px;
    height: 38px;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    font-size: 20px;
}
#pclass {
    width: 300px;
    height: 38px;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    font-size: 20px;
}
#weapon {
    width: 300px;
    height: 38px;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    font-size: 20px; 
}
.save-button {
    font-family: 'Germania One';
    padding: 15px;
    margin: 20px 100px 20px 100px;
    color: white;
    border-color:rgba(90, 90, 90, 0.75);
    background-color: rgba(197, 239, 255, 0.39);
    border-radius: 10px;
    font-size: 30px;
    text-shadow: 2px 2px rgba(0,0,0,0.8);
    width: 250px;
    height: 60px;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    transition: all .2s ease-in-out;
    display: flex;
    flex-direction: column;
    text-align: center;
    align-items: center;
    justify-content: center;
}
.save-button:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: pointer;
    background-color: rgb(12, 199, 143);
}
.char-display {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-evenly;
}
.name-bind {
    text-align: center;
    font-family: 'Germania One';
    font-size: 70px;
    margin: 30px 10px 10px 10px;
    color: rgb(216, 167, 122);
}
.stat-bind {
    display: flex;
    flex-direction: column;
    text-align: center;
    margin: 30px 10px 10px 40px;
    background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.2), rgba(115, 137, 179, 0.2), rgba(19, 18, 73, 0.3));
    border: 1px solid rgba(184, 184, 184, 0.4);
    border-radius: 10px;
    padding: 20px;
}
.stat-field {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 35px;
    color: rgb(200, 215, 238);
    margin: 0px 20px 0px 20px;
}
.spells {
    display: flex;
    flex-direction: row;
    text-align: center;
    flex-wrap: wrap;
    margin: 30px 10px 10px 40px;
    background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.2), rgba(115, 137, 179, 0.2), rgba(19, 18, 73, 0.3));
    border: 1px solid rgba(184, 184, 184, 0.4);
    border-radius: 10px;
    padding: 5px;
    max-height: 200px;
    max-width: 25%;
}
.details-tile {
    display: flex;
    flex-direction: row;
    text-align: center;
    flex-wrap: wrap;
    background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.2), rgba(115, 137, 179, 0.2), rgba(19, 18, 73, 0.3));
    border: 1px solid rgba(184, 184, 184, 0.4);
    border-radius: 10px;
    height: 130px;
    width: 350px;
    padding: 10px;
}
.spell-list {
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    font-family: 'Germania One';
    font-size: 30px;
    color:rgb(152, 179, 228);
}
.spell {
    font-family: 'Bellefair';
    font-size: 20px;
    padding: 0px 5px 0px 5px;
    margin: 3px 10px 3px 3px;
    background: rgba(0, 0, 0, 0.377);
    color: white;
    border-radius: 10px;
}
.spell-details {
    font-family: 'Bellefair';
    color: white;
    font-size: 20px;
    padding: 5px;
    display: flex;
    align-items: center;
}
.spell:hover {
    cursor: pointer;
    background: rgb(115, 137, 179);
    color: rgb(254, 255, 206);
}
.row {
    display: flex;
    flex-direction: row;
}
.desc-container {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    padding-top: 20px;
    width: 100%;
}
.field-description {
    width: 500px;
    height: 450px;
    background-image: linear-gradient(to bottom, rgba(94, 94, 94, 0.2), rgba(115, 137, 179, 0.2), rgba(0, 0, 0, 0.3));
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    color: white;
    margin-bottom: 40px;
    border: 1px solid rgba(184, 184, 184, 0.4);
    border-radius: 10px;
}
.field-header {
    text-align: center;
    font-family: 'Germania One';
    font-size: 48px;
    margin: 0px 0px 0px 0px;
    padding: 10px 0px 0px 0px;
    color: rgb(152, 179, 228);
    text-shadow: 1px 1px rgba(0, 0, 0, 0.5);
}
.desc-text {
    font-family: 'Bellefair';
    font-size: 23px;
    font-weight: 200;
    padding: 0 10px 15px 10px;
    margin-bottom: 0px;
    width: 90%;
}
.desc-mods {
    font-family: 'Bellefair';
    font-size: 22px;
    font-weight: 400;
    padding: 0 10px 0px 10px;
    margin-bottom: 0px;
    width: 90%;
}
</style>