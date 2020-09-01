<template>
  <div>
    <div v-if="$store.state.token != '' && $store.state.user.id === this.collection.userID">
        <a v-on:click="show"><font-awesome-icon :icon="['fa','cog']" class='icon' @mouseover="isHovering = true" 
      @mouseout="isHovering = false" 
      :class="{'fa-spin': isHovering}"/></a>
    </div>
        <modal name='modal-form' v-if="show" class='modal-content' height=385px width=285px styles="background-color: rgb(204, 204, 204); border-radius: 15px; border: 1px solid black">
            <form id="modal-inputs" v-on:submit.prevent="submitForm(collection.collectionID)"> 
                <label class="label" for="rename">Rename Collection</label>
                <input type="text" v-model="collection.name" />
                <label class="label" for="set-visibility">Visibilty</label>
                <select name='visibility' id='visibility' v-model="selected">
                    <option disabled value="">Please Select One</option>
                    <option>Public</option>
                    <option>Private</option>
                </select>
                <label class="label" for="set-visibility">Delete Collection</label>
                <select name='delete' id='delete' v-model="deleteCollection">
                    <option disabled value="">Are You Sure?</option>
                    <option>Yes</option>
                    <option>No</option>
                </select>
                <div class="actions">
                    <button class="submit" type='submit' style="background-color: black">Confirm</button>
                </div>
            </form>
        </modal>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';

export default {
    name: 'modal-form',
    props: ["collection"],
    data() {
        return {
            selected: '',
            deleteCollection: '',
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
        deleteThisCollection (id) {
            console.log(this.collection.collectionID);
            if (this.deleteCollection != '') {
                if (this.deleteCollection == "Yes") {
                    CollectionService.deleteCollection(id).then(response => {
                        if (response.status === 200) {
                            this.$router.push({name: 'my-collections', params: {id: this.$store.state.user.username}});
                        }
                    });
                }
                if (this.deleteCollection == 'No') {
                    console.log(this.collection.collectionID);
                }
            }
        },
        submitForm(id) {
            console.log(this.collection.name);
            if (this.deleteCollection != '') {
                if (this.deleteCollection == "Yes") {
                    CollectionService.deleteCollection(id).then(response => {
                        if (response.status === 200) {
                            this.$router.push({name: 'my-collections', params: {id: this.$store.state.user.username}});
                        }
                    });
                }
                if (this.deleteCollection == 'No') {
                    console.log(this.collection.collectionID);
                }
            }
            if (this.selected != '') {this.collection.visibility = this.selected}
            CollectionService.updateCollection(this.collection).then(response => {
                if (response.status === 201) {
                    console.log(response);
                    this.hide();
                }
            }).catch(error => {
                if (error.response) {
                     this.errorMsg = "Error updating collection. Response received was: " + error.response.statusText + "!";
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
.icon {
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