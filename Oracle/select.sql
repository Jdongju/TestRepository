
/*
--Select��
*/

--bwriter�� ȫ�浿�� ��� �Խù� ��������
select *
from board
where bwriter='ȫ�浿';
--bwrite�� ȫ�浿�� bno, btitle, bcontent�� ��������
select bno, BTITLE, BCONTENT
from board
where bwriter='ȫ�浿';

--bno�� 1~10 ������ �Խù��� �������ÿ�
select bno, BTITLE, BWRITER
from board
where bno>=1 and bno<=10;
--where bno between 1 and 10

--bwriter�� 'ȫ'�� �����ϴ� �Խù��� bno, bittle, bwriter�� �������ÿ�
select bno, BTITLE, BWRITER
from board
where bwriter LIKE '%ȫ%';

-- �Խù��� ���� �߿� '�ڹ�'�� ���ԵǾ� �ִ� �Խù��� bno, btitle, bwriter
select bno, BTITLE, BWRITER
from board
where btitle LIKE '%�ڹ�%';

-- �Խù��� ����, ���� �߿� '�ڹ�'�� ���ԵǾ� �ִ� �Խù��� ��ȣ, ����, �۾��̸� �������ÿ�

select bno, BTITLE, BWRITER, BCONTENT
from board
where btitle LIKE '%�ڹ�%' or bcontent LIKE '%�ڹ�%';

--�۾��̰� ���ڹ�,��浿, ������� �Խù��� ��ȣ, ����, �۾��� ������ ����������.


select bno, btitle, bwriter, bcontent
from board
--where bwriter='���ڹ�'or bwriter='��浿' or bwriter='�����';
where bwriter in('���ڹ�','��浿','�����');

-- ÷�������� ���� �Խù��� �������ÿ�
select *
from board
where BORIGINALFILENAME is null;
--÷�������� �ִ� �Խù��� �������ÿ�
select *
from board
where BORIGINALFILENAME is not null;

--�Խù��� �ۼ��� ����� �̸��� �������ÿ�(�ߺ��� �����ϰ�)

select DISTINCT bwriter from board;

--�Խñ��� �� ��¥�� 2016���� �Խñ��� �������ÿ�

SELECT * 
FROM BOARD 
where '2016.01.01'<=bdate and bdate<='2016.12.31';

SELECT * 
FROM BOARD 
where bdate between '2016.01.01'and '2016.12.31';

-- bno->��ȣ, btitle->����, bwriter->�۾��̷� �÷��̸��� �����ؼ� �������ÿ�

select bno as ��ȣ, btitle as ����, bwriter �۾���
from BOARD;

--����

-- �Խù��� ��ȣ�� �������� ������������ �������ÿ�
select *
from board
order by bno asc;

--2017�⵵�� �ۼ��� �Խù��� ��ȣ�� �������� ������������ �������ÿ�

select *
from BOARD
where bdate between '2017.01.01' and '2017.12.31'
ORDER BY bno desc; 

--�۾��̸� �������� 1�������ϰ� ����¥�� �������� 2�� �����Ͻÿ�
select *
from board
order by bwriter, BDATE desc;


--����¡ó��
--����Ǿ��ִ� ������� �� ��ȣ �ű��
select rownum, bno, btitle, bwriter,bdate, bhitcount
from board;
--���� ��, �� ��ȣ �ű��
select rownum, bno, btitle, bwriter, bdate, bhitcount
from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc);

--Ư�� ���ȣ ���ϸ� ��������(Top N)
select rownum, bno, btitle, bwriter, bdate, bhitcount
from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc)
where ROWNUM>=10;

-- ���� ���ȣ�� �� ���ȣ ������ �Խù� ��������
select r,bno, btitle, bwriter, bdate, bhitcount
from
  (select rownum as r, bno, btitle, bwriter, bdate, bhitcount
  from (
    select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc
    )
    --where rownum<=(pageNo*rowsPerPage)
  where ROWNUM<=(3*10)
  )
  --where r>=((pageNo-1)*rowsPerPage+1);
where r>=((3-1)*10+1);

/*
�� �� ���ϱ�
*/
-- ��ü ��� ���ϱ�
SELECT count(*) FROM board;
SELECT count(bno) FROM board;
SELECT count(boriginalfilename) FROM board;
--Ư�� ���ǿ� �´� �� �� ���ϱ�
select count(*) from board where bwriter<>'ȫ�浿';



