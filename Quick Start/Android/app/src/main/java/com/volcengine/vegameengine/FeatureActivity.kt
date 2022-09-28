package com.volcengine.vegameengine

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.blankj.utilcode.util.PermissionUtils
import com.volcengine.vegameengine.base.BaseSampleActivity
import com.volcengine.vegameengine.util.Feature

class FeatureActivity : BaseSampleActivity() {

    private var mFeatureId = -1

    private lateinit var etGameId: EditText
    private lateinit var etRoundId: EditText
    private lateinit var etClarityId: EditText
    private lateinit var btnStartGame: Button

//    private val testBean = TestBean("7104356860098059039") // 抖音
    private val testBean = TestBean("7137612624056851237") // 桌面宠物
//    private val testBean = TestBean("7068174254205967111") // 汤姆猫

    override fun onCreate(savedInstanceState: Bundle?) {
        mFeatureId = intent.getIntExtra("featureId", -1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startgame)
        initView()
    }

    private fun initView() {
        etGameId = findViewById(R.id.editText_gameId)
        etRoundId = findViewById(R.id.editText_roundId)
        etClarityId = findViewById(R.id.editText_clarityId)
        etGameId.setText(testBean.gameId)
        etRoundId.setText(testBean.roundId)
        etClarityId.setText(testBean.clarityId)

        btnStartGame = findViewById(R.id.btn_start_game)
        btnStartGame.setOnClickListener{
            if (etGameId.text.isNotEmpty()) {
                GameActivity.startGame(
                    etGameId.text.toString(),
                    etRoundId.text.toString(),
                    etClarityId.text.toString().toIntOrNull() ?: 1,
                    this,
                    mFeatureId
                )
            }
            else {
                Toast.makeText(this, "请输入gameId", Toast.LENGTH_SHORT).show()
            }
        }

        PermissionUtils.permission(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.BLUETOOTH,
        ).request()
    }

    override fun titleRes(): Int {
        when (mFeatureId) {
            Feature.FEATURE_AUDIO -> {
                return R.string.audio_title
            }
            Feature.FEATURE_CAMERA -> {
                return R.string.camera_title
            }
            Feature.FEATURE_CLIPBOARD -> {
                return R.string.clipboard_title
            }
            Feature.FEATURE_FILE_CHANNEL -> {
                return R.string.file_channel_title
            }
            Feature.FEATURE_LOCATION -> {
                return R.string.location_title
            }
            Feature.FEATURE_MESSAGE_CHANNEL -> {
                return R.string.message_channel_title
            }
            Feature.FEATURE_POD_CONTROL -> {
                return R.string.pod_control_title
            }
            Feature.FEATURE_SENSOR -> {
                return R.string.sensor_title
            }
            Feature.FEATURE_UNCLASSIFIED -> {
                return R.string.unclassified_title
            }
            else -> {
                return -1
            }
        }
    }
}