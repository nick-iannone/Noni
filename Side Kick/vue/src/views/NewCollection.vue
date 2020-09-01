<template>
    <div class="page">
        <collection-create/>
    </div>
</template>

<script>
import CollectionCreate from "@/components/CollectionCreate.vue"

export default {
    components: {
        CollectionCreate
    }
}
</script>

<style>
.page {
    display: flex;
    flex-direction: column;
}
</style>