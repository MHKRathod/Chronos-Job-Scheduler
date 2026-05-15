import JobForm from "./components/JobForms";
import JobList from "./components/JobList";
import JobLogs from "./components/JobLogs";
import FailedNotifications from "./components/FailedNotifications";

function App() {

    return (

        <div className="min-h-screen bg-gray-100 p-8">

              <h1 className="text-4xl font-bold bg-gradient-to-r from-purple-700 to-blue-600 text-white px-8 py-4 rounded-2xl shadow-lg">

        Chronos - Job Scheduler

    </h1>


            <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">

                <JobForm />

                <FailedNotifications />

                <JobList />

            </div>

            <div className="mt-8">

                <JobLogs />

            </div>

        </div>
    );
}

export default App;