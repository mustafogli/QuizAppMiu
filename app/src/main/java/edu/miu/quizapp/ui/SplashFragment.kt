package edu.miu.quizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import edu.miu.quizapp.R
import edu.miu.quizapp.db.Quiz
import edu.miu.quizapp.db.QuizDatabase
import edu.miu.quizapp.utils.BaseFragment
import edu.miu.quizapp.utils.PrefManager
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    private lateinit var tvWelcome: TextView
    private var prefManager: PrefManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prefManager = PrefManager(context)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        tvWelcome = view.findViewById(R.id.logo_welcome)
        addQuestionsToDB()
        return view
    }

    override fun onResume(){
        super.onResume()
        tvWelcome.postDelayed({
            if (!prefManager?.isFirstTimeLaunch()!!) {
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment)
            }else{
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_welcomeFragment)
            }
        }, 1000)
    }

    fun addQuestionsToDB(){
        val quiz1 = Quiz(1L,"1) Is it possible to have an activity without UI to perform action/actions?","A. Not possible", "B. Wrong question","C. Yes,it is possible","D. None of the above","c","Answer: (c) Generally, every activity is having its UI(Layout). But if a developer wants to create an activity without UI, he can do it.")
        val quiz2 = Quiz(2L,"2) On which thread services work in android?", "A. Worker Thread","B. Own Thread","C. Main Thread","D. None of the above","c","Answer: (c) Services, by default, work on Main thread. You can start services from any thread, but if you want to update the UI, you need to call Main thread.")
        val quiz3 = Quiz(3L,"3) On which thread broadcast receivers will work in android?","A. Worker Thread", "B. Own Thread","C. Activity Thread","D. None of the above","b","Answer: (b) Broadcast Receiver by default runs on Main Thread only.")
        val quiz4 = Quiz(4L,"4) What is the life cycle of broadcast receivers in android?","A. send intent()", "B. onRecieve()","C. implicitBroadcast()","D. all above","b","Answer: (b) Broadcast receiver has only onReceive() method. Broadcast starts from onRecieve() and control comes out from onRecieve().")
        val quiz5 = Quiz(5L,"5) What are the wake locks available in android?","A. PARTIAL_WAKE_LOCK", "B. SCREEN_DIM_WAKE_LOCK","C. SCREEN_BRIGHT_WAKE_LOCK","D. FULL_WAKE_LOCK","d","Answer: (d) When CPU is on mode, PARTIAL_WAKE_LOCK will be active.\n" +
                "\n" +
                "When CPU + bright Screen low is on mode, SCREEN_DIM_WAKE_LOCK will be active.\n" +
                "\n" +
                "When CPU + bright Screen High is on mode,SCREEN_BRIGHT_WAKE_LOCK will be active.\n" +
                "\n" +
                "When CPU, Screen, bright Screen High is on mode, FULL_WAKE_LOCK will be active.")
        val quiz6 = Quiz(6L,"6) What is log message in android?","A.  Log message is used to debug a program.", "B. Same as printf()","C. Same as Toast().","D. None of the above","a","Answer: (a) The log message is used to debug a program. Some of log messages are shown below\n" +
                "\n" +
                "log.d-Debugging log\n" +
                "log.i Informative log\n" +
                "log.e-Error log \n" +
                "log.w-Warning log\n" +
                "log.v-verbose log")
        val quiz7 = Quiz(7L,"7) What is the package name of JSON?","A. com.json", "B. in.json","C. com.android.JSON","D. org.json","d","Answer: (d) org.json is the package name of JSON object and JSON array")
        val quiz8 = Quiz(8L,"8)What are the JSON elements in android?","A. integer, boolean", "B. boolean","C. null","D. Number, string, boolean, null, array, and object","d","Answer: (d) Json elements are Number, string, boolean, null, array, and object")
        val quiz9 = Quiz(9L,"9) How many protection levels are available in the android permission tag?","A. There are no permission tags available in android", "B. Normal, kernel, application","C. Normal, dangerous, signature, and signatureOrsystem","D. None of the above","c","Answer: (c) Android is having four levels of protection in android permission tag. They are normal, dangerous, signature, and signatureOrsystem.")
        val quiz10 = Quiz(10L,"10) What are the main components in android?","A. Activity", "B. Services","C. Broadcast Receiver","D. all above","d","Answer: (d) The main components in android are Activity, services, Broadcast Receiver and content providers.")
        val quiz11 = Quiz(11L,"11) To add features, components, and permissions to your Android app, which file needs to be edited?","A. AndroidManifest.xml", "B. Components.xml","C. AppManifest.xml","D. ComponentManifest.xml","a","Answer: (a) - AndroidManifest.xml")
        val quiz12 = Quiz(12L,"12) Which XML attribute should be used to make an Image View accessible?","A. android:talkBack", "B. android:labelFor","C. android:hint","D. android:contentDescription","d","Answer: (d) - android:contentDescription")
        val quiz13 = Quiz(13L,"13)  You launch your app, and when you navigate to a new screen it crashes, Which action will NOT help you diagnose the issue?","A. Set breakpoints and then step through the code line by line", "B. Use the profiler tools in Android Studio to detect anomalies CPU, and network usage.","C. Add a Thread.sleep() call before you start the new activity.","inspect the logs in Logcat.","c","Answer: (c) - Add a Thread.sleep() call before you start the new activity.")
        val quiz14 = Quiz(14L,"14) Why might push notifications stop working?",
            "A. Battery optimization is turned on on the device", "B. The device token is not being sent to push provider correctly.","C. Google Play Services is not installed on the deivce/emulator.","D. All of the above","d","Answer: (d) All of the above")
        val quiz15 = Quiz(15L,"15) When will an activity's onActivityResult()be called?","A. when calling finish()in the parent activity", "B. when placing an app into the background by sitching to another app","C.  When onStop() is called in the target activity","D. when calling finish() in the target activity","d","Answer: (d) - when calling finish() in the target activity")

        launch {
            context?.let {
                QuizDatabase(it)
                    .getQuizDao().deleteAllQuiz()
                QuizDatabase(it)
                    .getQuizDao().addQuizzes(quiz1,quiz2,quiz3,quiz4,quiz5,quiz6,quiz7,quiz8,
                    quiz9,quiz10,quiz11,quiz12,quiz13,quiz14,quiz15)
            }
        }
    }

}