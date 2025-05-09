<script>
    export let data;
    import "../app.css";
    
    let { task } = data;  // Specific task
    let tasks = data.tasks; // All tasks from page.server.js

    import axios from "axios";
    import { goto, invalidate } from "$app/navigation";

    let taskname = task.title;
    let status = task.completed ?? false;

    let showSuccess = false;
    let showError = false;

    async function updateTask() {
        console.log("Updating Task:", task.id, taskname, status);

        const updatedTask = { 
            title: taskname, 
            completed: status
        };

        try {
            await axios.put(`http://localhost:9090/tasks/${task.id}`, updatedTask, {
                headers: { "Content-Type": "application/json" }
            });

            // ✅ Update locally before navigation
            //data.tasks = tasks.map(t => t.id === task.id ? { ...t, ...updatedTask } : t);

            console.log("Task Added:", updatedTask); // Debugging

            showSuccess = true;
            showError = false;

            setTimeout(async () => {
                await invalidate('http://localhost:9090/tasks'); // ✅ Force re-fetch task list
                await goto('/showTasks');  // Navigate first
            }, 1500);
            
        } catch (error) {
            console.error("Error updating task:", error);
            showError = true;
            showSuccess = false;
        }
    }
</script>

<!-- Update Form -->
<form on:submit|preventDefault={updateTask}>
    <input type="text" bind:value={taskname} placeholder="Task" required />
    
    <label>
        <input type="checkbox" bind:checked={status} /> Completed?
    </label>

    <button type="submit">Update</button>
</form>

{#if showSuccess}
    <div class="notification success">✅ Task Updated Successfully!</div>
{/if}

{#if showError}
    <div class="notification error">❌ Failed to Update Task. Try Again!</div>
{/if}
