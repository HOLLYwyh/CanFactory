package Management.HumanResources.test;

import Management.HumanResources.DepartmentCommand.AuditSalaryTableCommand;
import Management.HumanResources.FinancialSystem.DataAccessObject.SalaryDaoImpl;
import Management.HumanResources.FinancialSystem.FinancialDepartment;
import Management.HumanResources.FinancialSystem.Permission;
import Management.HumanResources.Manager.TestingManager;
import Management.HumanResources.Staff.Auditor;
import Management.HumanResources.Staff.Worker;
import Management.HumanResources.TeamLeader.TestingTeamLeader;
import Management.QualityTesting.QualityAssuranceDepartment;
import Presentation.Protocol.OutputManager;

import java.io.IOException;

/**
 * FinancialDepartmentTest类的测试类
 *
 * @author 陈垲昕
 * @since 2021/10/24 3:18 下午
 */

public class FinancialDepartmentTest {


    public static void main(String[] args) throws IOException {

        OutputManager.getInstance().setLanguage(OutputManager.Lang.zh_CN);
        // 设置语言

        // 获取QualityAssurance部门的实例
        QualityAssuranceDepartment qualityTestingDepartment = QualityAssuranceDepartment.getInstance();

        // 获取FinancialDepartment部门的实例
        FinancialDepartment financialDepartment = FinancialDepartment.getInstance();
        // 创建该部门的经理
        TestingManager testingManager = new TestingManager();
        // 创建该部门的一个组长
        TestingTeamLeader testingTeamLeader1 = new TestingTeamLeader();
        // 创建该部门的一个员工
        Worker testingWorker = new Worker();

        //创建经济部门的审计员等
        Auditor auditor1=new Auditor("怀特菲尔德", 201.0);
        Auditor auditor2=new Auditor("哈梅特",100.0);
        if(auditor1 instanceof Permission){
            financialDepartment.register(auditor1);

        } else{
            OutputManager.getInstance().errorMassage(
                    "没有权限访问财务系统，访问已被拒绝",
                    "沒有權限訪問財務系統，訪問已被拒絕",
                    "The access to financial system is rejected"
            );
        }

        if(auditor2 instanceof Permission){
            financialDepartment.register(auditor2);
        } else{
            OutputManager.getInstance().errorMassage(
                    "没有权限访问财务系统，访问已被拒绝",
                    "沒有權限訪問財務系統，訪問已被拒絕",
                    "The access to financial system is rejected"
            );
        }



        testingManager.setName("Bear");

        testingTeamLeader1.setName("梁乔");
        testingTeamLeader1.setLeader(testingManager);

        testingWorker.setName("桥梁");
        testingWorker.setLeader(testingTeamLeader1);

        if(testingWorker instanceof Permission){
            financialDepartment.register(testingWorker);
        } else{
            OutputManager.getInstance().errorMassage(
                    testingWorker.getName()+ "没有权限访问财务系统，访问已被拒绝",
                    testingWorker.getName()+"沒有權限訪問財務系統，訪問已被拒絕",
                    testingWorker.getName()+"The access to financial system is rejected"
            );
        }
        // 分别将其注册到该部门
        qualityTestingDepartment.register(testingManager);
        qualityTestingDepartment.register(testingTeamLeader1);
        qualityTestingDepartment.register(testingWorker);


        //审计命令，指定经济部门下的一个审计员
        AuditSalaryTableCommand auditReportCommand = new AuditSalaryTableCommand(auditor1);

        //命令模式，执行命令
        financialDepartment.setCurrentCommand(auditReportCommand);


        //获取SalaryDaoImpl单例
        SalaryDaoImpl salaryDaoImpl= SalaryDaoImpl.getInstance();

        financialDepartment.giveCommand();
        // 理论输出： 无需审计


        //写入QualityTestingDepartment
        salaryDaoImpl.saveSalary(qualityTestingDepartment);
        salaryDaoImpl.saveSalary(financialDepartment);

        salaryDaoImpl.closeFile();

        financialDepartment.giveCommand();
        //理论输出: 审计

        financialDepartment.giveCommand();
        //理论输出： 无需审计


        financialDepartment.viewAuditHistoryList();
    }
}
