<template>
  <div class="collection">
    <div class="opt-nav">

      <div class="radio-filters">
        <input
          type="radio"
          id="cards"
          value="cards"
          v-model="cardsOrContracts"
          name="cardsOrContracts"
        />
        <label for="cards">Cards</label>
        <input
          type="radio"
          id="contracts"
          value="contracts"
          v-model="cardsOrContracts"
          name="cardsOrContracts"
        />
        <label for="contracts">Contracts</label>
      </div>

      <input
        class="search-input"
        type="text"
        v-model="search"
        placeholder="Filter Collection..."
      />

      <div class="radio-filters">
        <input
          type="radio"
          v-model="filter"
          value="name"
          id="name"
          name="filter"
        />
        <label for="name">Name</label>
        <br />
        <input
          type="radio"
          v-model="filter"
          value="cost"
          id="cost"
          name="filter"
        />
        <label for="cost">Cost</label>
        <br />
        <input
          type="radio"
          v-model="filter"
          value="description"
          id="description"
          name="filter"
        />
        <label for="description">Description</label>
        <br />
        <input
          type="radio"
          v-model="filter"
          value="attack"
          id="attack"
          name="filter"
          v-if="cardsOrContracts == 'cards'"
        />
        <label for="attack" v-if="cardsOrContracts == 'cards'">Attack</label>
        <br />
        <input
          type="radio"
          v-model="filter"
          value="resilience"
          id="resilience"
          name="filter"
          v-if="cardsOrContracts == 'cards'"
        />
        <label for="resilience" v-if="cardsOrContracts == 'cards'"
          >Resilience</label
        >
        <br />
        <input
          type="radio"
          v-model="filter"
          value="loyalty"
          id="loyalty"
          name="filter"
          v-if="cardsOrContracts == 'cards'"
        />
        <label for="loyalty" v-if="cardsOrContracts == 'cards'">Loyalty</label>
        <br />
        <input
          type="radio"
          v-model="filter"
          value="duration"
          id="duration"
          name="filter"
          v-if="cardsOrContracts == 'contracts'"
        />
        <label for="duration" v-if="cardsOrContracts == 'contracts'"
          >Duration</label
        >
        <br />
      </div>
    </div>

    <div class="collection-display" v-if="cardsOrContracts == 'cards'">
      <card v-for="card in pageOfItems" :card="card" :key="card.id" />
    </div>

    <div class="collection-display" v-if="cardsOrContracts == 'contracts'">
      <contract-collection
        v-for="contract in pageOfItems"
        :contract="contract"
        :key="contract.id"
      />
    </div>

    <div class="page-tracker">
      <jw-pagination
        :disableDefaultStyles="true"
        :items="filteredList"
        @changePage="onChangePage"
        :pageSize="8"
      ></jw-pagination>
    </div>
  </div>
</template>

<script>
import Card from "@/components/Card.vue";
import ContractCollection from "@/components/ContractCollection.vue";
import CardService from "../services/CardService.js";

export default {
  data() {
    return {
      cards: [],
      contracts: [],
      pageOfItems: [],
      search: "",
      filter: "",
      cardsOrContracts: "cards",
    };
  },
  components: {
    Card,
    ContractCollection,
  },
  computed: {
    filteredList() {
      if (this.cardsOrContracts === "cards") {
        return this.cards.filter((card) => {
          if (this.filter === "") {
            return card.name.toLowerCase().includes(this.search.toLowerCase());
          }
          if (this.filter === "name") {
            return card.name.toLowerCase().includes(this.search.toLowerCase());
          }
          if (this.filter === "cost") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return card.cost == this.search;
          }
          if (this.filter === "description") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            if (card.abilities != null) {
              return card.abilities
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
          }
          if (this.filter === "attack") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return card.attack == this.search;
          }
          if (this.filter === "resilience") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return card.resilience == this.search;
          }
          if (this.filter === "loyalty") {
            if (!this.search) {
              return card.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return card.loyalty == this.search;
          }
        });
      } else if (this.cardsOrContracts === "contracts") {
        return this.contracts.filter((contract) => {
          if (this.filter === "") {
            return contract.name
              .toLowerCase()
              .includes(this.search.toLowerCase());
          }
          if (this.filter === "name") {
            return contract.name
              .toLowerCase()
              .includes(this.search.toLowerCase());
          }
          if (this.filter === "cost") {
            if (!this.search) {
              return contract.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return contract.cost == this.search;
          }
          if (this.filter === "description") {
            if (!this.search) {
              return contract.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            if (contract.desc != null) {
              return contract.desc
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
          }
          if (this.filter === "duration") {
            if (!this.search) {
              return contract.name
                .toLowerCase()
                .includes(this.search.toLowerCase());
            }
            return contract.dura == this.search;
          }
        });
      } else return null;
    },
  },
  methods: {
    backToMain() {
      this.$router.push({ name: "home" });
    },
    onChangePage(pageOfItems) {
      // update page of items
      this.pageOfItems = pageOfItems;
    },
  },
  created() {
    CardService.getAllCards().then((response) => {
      this.cards = response.data;
      this.cards.sort((a, b) => (a.name > b.name ? 1 : -1));
      this.filteredList = this.cards;
    });
    CardService.getAllContracts().then((response) => {
      this.contracts = response.data;
      this.contracts.sort((a, b) => (a.name > b.name ? 1 : -1));
    });
  },
};
</script>

<style>
.collection {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-image: url("../assets/background_1.png");
  height: 1002px;
  width: 1920px;
}
.collection-display {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
  /* padding: 20px; */
  width: 70%;
  height: 810px;
}

/* PAGINATION  //////////////////////////////////////////////////////////////////////////////////////////  */
.pagination {
  justify-content: center;
  flex-wrap: wrap;
  flex-direction: row;
  display: flex;
  gap: 50px;
}
.pagination li {
  font-family: "Marcellus";
  color: white;
  font-weight: 600;
  font-size: 20px;
  list-style: none;
}
.pagination li:hover {
  cursor: pointer;
  color: rgb(138, 138, 241);
}
.page-tracker {
  display: flex;
  flex-direction: row;
  justify-content: center;
  background-color: rgba(37, 37, 37, 0.8);
  width: 700px;
  height: 60px;
  margin-top: 20px;
  margin-bottom: 20px;
  border-radius: 15px;
  text-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}

/* FILTERS AND NAVIGATION   //////////////////////////////////////////////////////////////////////////////////////////  */
.opt-nav {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: center;
  align-items: center;
  gap: 50px;
}
.nav-btn {
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(60deg, rgba(3, 24, 49, 0.8), rgb(34, 34, 92, 0.8));
  color: white;
  border: 1px solid white;
  border-radius: 5px;
  width: 150px;
  height: 50px;
  margin: 10px;
  font-family: "Marcellus";
  font-weight: 600;
  font-size: 20px;
  transition: all 0.2s ease-in-out;
  box-shadow: 3px 3px rgb(0, 0, 0, 0.5);
}
.nav-btn:hover {
  transform: scale(1.04);
  cursor: pointer;
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(180deg, rgba(37, 122, 226, 0.8), rgba(0, 0, 105, 0.8));
}
.radio-filters {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;
  padding: 10px;
  border-radius: 10px;
  background-color: rgba(37, 37, 37, 0.8);
  font-family: "Marcellus";
  font-size: 17px;
  color: white;
  margin: 0px 15px 0px 15px;
  height: 30px;
}
.search-input {
  height: 40px;
  width: 280px;
  font-family: "Marcellus";
  font-size: 20px;
  border: 1px solid black;
  border-radius: 5px;
  background-color: rgba(255, 255, 255, 0.4);
}
</style>
