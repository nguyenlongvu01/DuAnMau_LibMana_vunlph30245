package com.vunlph30245.duanmau_libmana;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.vunlph30245.duanmau_libmana.DAO.PhieuMuonDAO;
import com.vunlph30245.duanmau_libmana.DAO.ThuThuDAO;
import com.vunlph30245.duanmau_libmana.DoanhThu.DoanhThuFragment;
import com.vunlph30245.duanmau_libmana.DoiMk.DoiMKFragment;
import com.vunlph30245.duanmau_libmana.Fragment.WelcomeFragment;
import com.vunlph30245.duanmau_libmana.LoaiSach.FragmentLoaiSach;
import com.vunlph30245.duanmau_libmana.Model.ThuThu;
import com.vunlph30245.duanmau_libmana.PhieuMuon.PhieuMuonFragment;
import com.vunlph30245.duanmau_libmana.Sach.SachFragment;
import com.vunlph30245.duanmau_libmana.ThanhVien.ThanhVienFragment;
import com.vunlph30245.duanmau_libmana.themUser.ThemUserFragment;
import com.vunlph30245.duanmau_libmana.topTen.TopTenFragment;

public class MainActivity extends AppCompatActivity {
    private static final int FRAGMENT_PHIEUMUON = 0;
    private static final int FRAGMENT_SACH = 1;
    private static final int FRAGMENT_LOAISACH = 2;
    private static final int FRAGMENT_THANHVIEN = 3;
    private static final int FRAGMENT_TOP10 = 4;
    private static final int FRAGMENT_DOANHTHU = 5;
    private static final int FRAGMENT_THEMNGUOIDUNG = 6;
    private static final int FRAGMENT_DOIMK = 7;
    PhieuMuonDAO dao;
    ThuThuDAO thuThuDAO;
    private DrawerLayout drawerLayout;
    private View headerView;
    private TextView tvUser;
    private final int currentFragment = FRAGMENT_PHIEUMUON;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        manager.beginTransaction()
                .replace(R.id.content_fame, phieuMuonFragment)
                .commit();


        //show user header
        headerView = navigationView.getHeaderView(0);
        tvUser = headerView.findViewById(R.id.tvUser);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        tvUser.setText("Welcome" + "!");
        //admin co quyen add user
        if (user != null && user.equalsIgnoreCase("admin")) {
            navigationView.getMenu().findItem(R.id.sub_ThemUser).setVisible(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                int itemID=item.getItemId();
                if(itemID==R.id.navPhieuMuon){
                    setTitle("Quản lý phiếu mượn");
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, phieuMuonFragment)
                            .commit();
                } else if (itemID==R.id.navLoaiSach) {
                    setTitle("Quản lý  Sách");
                    FragmentLoaiSach loaiSachFragment = new FragmentLoaiSach();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, loaiSachFragment)
                            .commit();
                } else if (itemID==R.id.navSach) {
                    setTitle("Quản lý Loại sách");
                    SachFragment sachFragment = new SachFragment();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, sachFragment)
                            .commit();
                } else if (itemID==R.id.navThanhVien) {
                    setTitle("Quản lý thành viên");
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, thanhVienFragment)
                            .commit();
                } else if (itemID==R.id.sub_Top) {
                    setTitle("Top 10 sách mượn nhiều nhất");
                    TopTenFragment topTenFragment = new TopTenFragment();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, topTenFragment)
                            .commit();
                } else if (itemID==R.id.sub_DoanhThu) {
                    setTitle("Doanh Thu");
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, doanhThuFragment)
                            .commit();
                } else if (itemID==R.id.sub_ThemUser) {
                    setTitle("Quản lý thêm người dùng");
                    ThemUserFragment themNguoiDungFragment = new ThemUserFragment();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, themNguoiDungFragment)
                            .commit();
                } else if (itemID==R.id.sub_DoiMK) {
                    setTitle("Đổi mật khẩu");
                    DoiMKFragment changePassFragment = new DoiMKFragment();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, changePassFragment)
                            .commit();
                } else if (itemID==R.id.sub_DangXuat) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else if (itemID==R.id.navcHAO) {
                    setTitle("Chao");
                    WelcomeFragment welcomeFragment = new WelcomeFragment();
                    manager.beginTransaction()
                            .replace(R.id.content_fame, welcomeFragment)
                            .commit();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fame, fragment);
        transaction.commit();
    }
}
