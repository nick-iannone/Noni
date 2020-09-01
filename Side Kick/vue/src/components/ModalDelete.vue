<template>
  <div>
    <div v-if="$store.state.token != '' && $store.state.user.id === this.collection.userID">
        <a v-on:click="show"><font-awesome-icon :icon="['fa','trash-alt']" class='trash' @mouseover="isHovering = true" 
      @mouseout="isHovering = false"/></a>
    </div>
        <modal name='modal-form' v-if="show" class='modal-content' width=285px styles="background-color: rgb(204, 204, 204); border-radius: 15px; border: 1px solid black">
            <form id="modal-inputs" v-on:submit.prevent="submitForm"> 
                <label class="label" for="set-visibility">Are You Sure?</label>
                <select name='visibility' id='visibility' v-model="selected">
                    <option disabled value="">Please Select One</option>
                    <option>Yes, delete this collection.</option>
                    <option>No, on second thought... </option>
                </select>
                <div class="actions">
                    <button class="submit" type='submit' style="background-color: black">DELETE</button>
                </div>
            </form>
        </modal>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';

export default {
    name: 'modal-delete',
    props: ["collection"],
    data() {
        return {
            selected: '',
            isHovering: false
        }
    },
    methods: {
        show () {
            this.$modal.show('modal-form');
        },
        hide () {
            this.$modal.hide('modal-form');
        },
        submitForm() {
            if (this.selected === "Yes, delete this collection.") {
                CollectionService.deleteCollection(this.collection.collectionID).then(response => {
                    if (response.status === 200) {
                        location.reload();
                    }      
            }).catch(error => {
                if (error.response) {
                     this.errorMsg = "Error deleting collection. Response received was: " + error.response.statusText + "!";
                 }
                 else if (error.request) {
                     this.errorMsg = "Server unavailable";
                 }
                 else {
                     this.errorMsg = "General Error";
                 }
                 console.log(error.status);
            });
        }
    },
    mount () {
        this.show()
    }
}
}
</script>

<style>
#modal-inputs {
    display: flex;
    flex-direction: column;
    font-family: 'Miriam Libre';
    font-size: 20px;
    margin: 40px;
    width: 200px;
    align-items: center;
}
.label {
    margin:10px;
}

.submit {
    margin-top: 30px;
    background-color: black;
    font-family: 'Bangers';
    color: white;
    font-size: 25px;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 20px;
    padding-right: 20px;
    border-radius: 15px;
    box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
    border: 0.5px solid grey;
}

.modal-content {
    background-color: rgba(0,0,0,0.4);
    color: black;
    font-weight: bolder;
}
.trash {
    color: white; 
    width: 50px;
    filter:drop-shadow(2px 2px 2px black);
    font-size: 60px;
    margin: 20px;
    margin-bottom:0px;

}
.fa-spin {
    color: lime;
}

</style>