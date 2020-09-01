<template>
<div  id="results">
    <button type='submit' class='back-btn' @click.prevent="reloadSearchTerm">Back to Volumes</button>
    <div>
        <div class="results-container">
            <issue-result v-for="issue in issueList" :key="issue.id" :issue="issue" :collections="collections"/>
        </div>
    </div>
</div>
</template>

<script>
import IssueResult from '@/components/IssueResult.vue'
import ComicService from '../services/ComicService'
import CollectionService from '../services/CollectionService'

export default {
    components: {
        IssueResult
    },
    data() {
        return {
            issueList: {},
            collections: {}
        }
    },
    methods: {
        reloadSearchTerm() {
            this.$router.push({name: 'search'});
        }
    },
    created() {
        ComicService.getIssuesByVolume(this.$route.params.id).then(response => {
            this.issueList = response.data;
        });
        CollectionService.getCollectionsByUsername(this.$store.state.user.username).then(response => {
            this.collections = response.data;
        });
    }
}
</script>

<style>
#results {
    background-image: url('../assets/iron-grip.png'), linear-gradient(90deg, rgba(2,31,73,1) 2%, rgba(13,133,182,1) 37%, rgb(2, 119, 113) 59%, rgb(1, 71, 49) 82%);
    padding-top: 40px;
}
.back-btn {
  font-family: 'Bangers';
  color: #fff;
  border-color:rgba(90, 90, 90, 0.75);
  background-color: rgba(45, 4, 61, 0.5);
  border-radius: 10px;
  font-size: 30px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
  width: 230px;
  height: 60px;
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transition: all .2s ease-in-out;
  display: flex;
  flex-direction: column;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  align-items: center;
  justify-content: center;
  margin-bottom: 40px;
}
.back-btn:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
    background-color: rgb(0, 175, 0);
}
</style>