package com.gialen.dao;

import java.io.Serializable;
import java.util.List;

/**
 * ͨ��Dao�ӿ�
 * @author Zhang Kaitao
 *
 * @param <M> ģ�Ͷ���
 * @param <PK> ����
 */
public interface IBaseDao<M extends Serializable, PK extends Serializable> {
    
    /**
     * ����ģ�Ͷ���
     * @param model ģ�Ͷ���
     * @return ��������
     */
    public void save(M model);

    /**
     * ��������ģ�Ͷ���
     * @param model ģ�Ͷ���
     */
    public void saveOrUpdate(M model);
    
    /**
     * ����ģ�Ͷ���
     * @param model ģ�Ͷ���
     */
    public void update(M model);
    
    /**
     * 
     * �ϲ�ģ�Ͷ���״̬���ײ�Ự
     * @param model
     */
    public void merge(M model);
    
    /**
     * ɾ��ģ�Ͷ���
     * @param id ����
     */
    public void delete(PK id);
    
//    /**
//     * ɾ��ģ�Ͷ���
//     * @param model ģ�Ͷ���
//     */
//    public void deleteObject(M model);
    
    /**
     * ����������ȡģ�Ͷ���
     * @param id ����
     * @return ����ģ�Ͷ���
     */
    public M get(PK id);
    
    /**
     * ͳ��ģ�Ͷ����Ӧ���ݿ���еļ�¼��
     * @return �������ݿ��ļ�¼��
     */
    public int countAll();
    
    /**
     * 
     * @return ��������ģ�Ͷ���
     */
    public List<M> listAll();

    /**
     * ��ҳ��ȡ����ģ�Ͷ���
     * @param pn ҳ�� ��1��ʼ
     * @param pageSize ÿҳ��¼��
     * @return
     */
    public List<M> listAll(int pn, int pageSize);
    
//    /**
//     * �Ƿ����ָ�������ļ�¼
//     * @param id ����
//     * @return true��ʾ������Ӧ��¼��false��ʾ������
//     */
//    boolean exists(PK id);
    
//    /**
//     * ˢ�µײ�session���󣨿��ܶԲ���ʵ����Ч��JDBC��
//     */
//    public void flush();
//    
//    /**
//     * ִ�еײ�session�����clear���������ܶԲ���ʵ����Ч��JDBC��
//     */
//    public void clear();

}
