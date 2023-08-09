package com.a103.eyakrev1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class AlarmListAdapter (val context: Context, val medicineRoutineList: ArrayList<MedicineRoutine>) : BaseAdapter() {

    // https://blog.yena.io/studynote/2017/12/01/Android-Kotlin-ListView.html
    override fun getCount(): Int {
        return medicineRoutineList.size
    }

    override fun getItem(position: Int): Any {
        return medicineRoutineList[position]
    }

    override fun getItemId(position: Int): Long {
        // 나중에 약의 고유 id를 받던가 그렇게 하자 => DB 보고 추후에 결정
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /* LayoutInflater는 item을 Adapter에서 사용할 View로 부풀려주는(inflate) 역할을 한다. */
        val view: View = LayoutInflater.from(context).inflate(R.layout.alarm_tab_list_view_item, null)

        /* 위에서 생성된 view를 alarm_tab_list_view_item.xml 파일의 각 View와 연결하는 과정이다. */
        val medicineTimeTextView = view.findViewById<TextView>(R.id.medicine_time)
        val medicineNameTextView = view.findViewById<TextView>(R.id.routineCategoryName)
        val medicineTimeLeftTextView = view.findViewById<TextView>(R.id.medicine_time_left)
        val medicineEatButton = view.findViewById<Button>(R.id.medicine_eat_button)

        /* ArrayList<MedicineAlarm>의 변수 medicineAlarm의 이미지와 데이터를 ImageView와 TextView에 담는다. */
        val medicineRoutine = medicineRoutineList[position]

        medicineTimeTextView.text = medicineRoutine.routineTime
        medicineNameTextView.text = medicineRoutine.routineName

        // https://stackoverflow.com/questions/3135112/android-nested-listview
        val prescriptionDetailLayout = view.findViewById<LinearLayout>(R.id.prescriptionDetailLayout)



        // 임시 설정 => 나중에 타이머 설정해야 함
//        medicineTimeLeftTextView.text = "00:31:58"

        return view
    }

}