@Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tvId = (TextView) findViewById(R.id.tvId);
    beaconManager = new BeaconManager(this);
    isConnected = false; // add this below: beaconManager.setRangingListener(new BeaconManager.RangingListener() { @Override public void onBeaconsDiscovered(Region region, List list) { if (!list.isEmpty()) { Beacon nearestBeacon = list.get(0); Log.d("Airport", "Nearest places: " + nearestBeacon.getRssi()); // nearestBeacon.getRssi() : 비콘의 수신 강도 tvId.setText(nearestBeacon.getRssi() + ""); // 수신강도가 -70 이상일때 알림창을 띄운다. if( !isConnected && nearestBeacon.getRssi() > -70) { isConnected = true; AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this); dialog.setTitle("알림") .setMessage("비콘이 연결되었습니다.") .setPositiveButton("확인", new DialogInterface.OnClickListener() { @Override public void onClick(DialogInterface dialog, int which) { } }) .create().show(); } else if( isConnected && nearestBeacon.getRssi() < -70 ) { Toast.makeText(MainActivity.this, "연결이 끊어졌습니다.", Toast.LENGTH_SHORT).show(); isConnected = false; } } } }); region = new Region("ranged region", UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"), null, null); // 본인이 연결할 Beacon의 ID와 Major / Minor Code를 알아야 한다. }

