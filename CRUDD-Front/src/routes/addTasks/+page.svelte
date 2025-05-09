<script>
    import axios from "axios";
    import "../app.css";
    
    // svelte-ignore export_let_unused
        export let data;
import { goto } from "$app/navigation";

let taskname = "";
let status = false;
let showSuccess = false;
let showError = false;

async function addTask() {
    console.log("Adding Task:", taskname, status); // Debugging

    const newTask = { 
        title: taskname, 
        completed: status // Make sure 'status' matches backend type (Boolean)
    };

    try {
        const response = await axios.post("http://localhost:9090/tasks", newTask, {
            headers: { "Content-Type": "application/json" }
        });

        console.log("Task Added:", response.data); // Debugging

        showSuccess = true;
        showError = false;

        data.tasks = [...data.tasks, newTask];

        setTimeout(() => {
            goto('/showTasks'); // Redirect after success
        }, 1500);
    } catch (error) {
        console.error("Error adding task:", error);

        showError = true;
        showSuccess = false;
    }
}

</script>

<form on:submit|preventDefault={addTask}>
    <input type="text" bind:value={taskname} placeholder="Task" required />
    
    <label>
        <input type="checkbox" bind:checked={status} /> Completed?
    </label>

    <button type="submit">Add</button>
</form>

<!-- ✅ Success Notification -->
{#if showSuccess}
    <div class="notification success">✅ Task Added Successfully!</div>
{/if}

<!-- ❌ Error Notification -->
{#if showError}
    <div class="notification error">❌ Failed to Add Task. Try Again!</div>
{/if}

<style>
    .notification {
        position: fixed;
        bottom: 20px;
        right: 20px;
        padding: 10px 15px;
        border-radius: 5px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
        color: white;
        font-weight: bold;
    }

    .success {
        background: #4caf50;
    }

    .error {
        background: #f44336;
    }
</style>
