
<script>
    import { goto } from '$app/navigation';
    import "../app.css";
    
    function showTasks() {
        goto('/showTasks');
    }

    function addTasks() {
        goto('/addTasks')
    }
</script>

    <h1>Task Management System</h1>
<button on:click={showTasks}>Show All Tasks</button>
<button on:click={addTasks}>Add Task</button>
